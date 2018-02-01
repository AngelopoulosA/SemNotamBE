package app.Repository;

import app.Model.*;
import app.Model.Operations.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class ComposedOperationLogic {

    private final ContextDBRepository contextDBRepository;
    private final UserRepository userRepository;
    private final OperationRepository operationRepository;
    private final ComposedOperationRepository composedOperationRepository;
    private final MessageRepository messageRepository;

    public ComposedOperationLogic(ContextDBRepository contextDBRepository, UserRepository userRepository, OperationRepository operationRepository, ComposedOperationRepository composedOperationRepository, MessageRepository messageRepository) {
        this.contextDBRepository = contextDBRepository;
        this.userRepository = userRepository;
        this.operationRepository = operationRepository;
        this.composedOperationRepository = composedOperationRepository;
        this.messageRepository = messageRepository;
    }

    /**
     * Checks if a certain Operation would be allowed within the current pending Operation.
     * Creates the Messages for this Operation.
     * Saves the Operation.
     * @param fl
     * @param operation
     * @param userId
     * @return true whether this Operation has no Messages and therefore the actual execution can be done immediately
     * @throws IOException
     */
    public boolean checkAndStartOperation(Flora2Repository fl, ComposedOperation operation, Long userId) throws IOException {
        User user = userRepository.findOne(userId);
        if(user == null) {
            throw new InvalidUserException();
        }
        operation.setExecutedBy(user);
        operation.setUserId(userId);
        operation.setExecutedAt(new Date());
        ComposedOperation pending = getPendingOperation();
        if (pending == null) {
            fl.startTransaction();
        } else if (pending.checkIfAllowed(operation, user)) {
            operation.setParent(pending);
            operation.setParentId(pending.getId());
        } else {
            throw new InvalidOperationException();
        }
        List<Message> messages = operation.generateMessages(contextDBRepository);
        messageRepository.save(messages);
        operationRepository.save(operation);
        for (Message m : messages) {
            SendMessage sm = new SendMessage(operation.getId(), "", userId, m);
            operationRepository.save(sm);
            operation.getOperations().add(sm);
        }

        if(messages.size() == 0) { // Nothing to be acknowledged we would need to wait on
            //boolean executionResult = operation.onExecuted(contextDBRepository);
            //if (executionResult) {
            operation.setExecuted(true);
            operationRepository.save(operation);
            return true;
            //}
        }
        return false;
    }

    /**
     * Gets the currently Pending Operation (first non executed one in the Tree) if existing
     * @return null if no Pending Operation exits
     */
    public ComposedOperation getPendingOperation () {
        ComposedOperation root = composedOperationRepository.findFirstByParentIsNull();
        if(root != null) {
            return root.findCurrentOperation();
        }
        return null;
    }

    /**
     * Acknowledges a SendMessage Operation and finishes the Parent Operation when this was the last Message for it.
     * Adds an AcknowledgeMessage Operation to the pending Parent Operation.
     * @param sendMessage
     * @param userId
     * @return false if the SendMessage can't be Acknowledged at the moment (because it is not Pending)
     */
    public boolean acknowledgeMessage(SendMessage sendMessage, Long userId) {
        ComposedOperation pending = getPendingOperation();
        ComposedOperation parent = sendMessage.getParent();
        if (pending.getId() == parent.getId()) { // We can only acknowledge (Send)Messages, which are direct childs of the Pending Operation
            sendMessage.setExecuted(true);
            boolean allAcknowledged = parent.getOperations().stream()
                    .allMatch(o -> o.isExecuted());
            if (allAcknowledged) {
                boolean executionResult = parent.onExecuted(contextDBRepository);
                if (executionResult) {
                    parent.setExecuted(true);
                    operationRepository.save(parent);

                } else {
                    return false;
                }
            }
            AcknowledgeMessage acknowledgement = new AcknowledgeMessage(parent.getId(), new Date(), userId, sendMessage.getMessage());
            operationRepository.save(acknowledgement);
            operationRepository.save(sendMessage);
            return true;
        } else {
            return false;
        }

    }

    /**
     * handles a Fatal Flora2 Error (possible Syntax Error) and sets FatalError for this Operation
     * @param operation
     */
    public void handleFatalError(ComposedOperation operation) {
        operation.setFatalError(true);
        operation.setExecuted(false);
        operationRepository.save(operation);
    }
}
