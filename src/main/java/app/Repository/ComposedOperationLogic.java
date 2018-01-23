package app.Repository;

import app.Model.*;
import app.Model.Operations.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

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

    public boolean checkOperation(Flora2Repository fl, ComposedOperation operation, Long userId) throws Exception {
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
        } else if (pending.checkIfAllowed(operation)) {
            operation.setParent(pending);
            operation.setParentId(pending.getId());
        } else {
            throw new InvalidOperationException();
        }
        List<Message> messages = operation.generateMessages();
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


    public ComposedOperation getPendingOperation () {
        ComposedOperation root = composedOperationRepository.findFirstByParentIsNull();
        if(root != null) {
            return root.findCurrentOperation();
        }
        return null;
    }

    public boolean acknowledgeMessage(SendMessage sendMessage, Long userId) {
        ComposedOperation pending = getPendingOperation();
        ComposedOperation parent = sendMessage.getParent();
        if (pending.getId() == parent.getId()) {
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
}
