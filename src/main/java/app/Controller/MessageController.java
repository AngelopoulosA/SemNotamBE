package app.Controller;

import app.Model.ComposedOperation;
import app.Model.Message;
import app.Model.SendMessage;
import app.Model.User;
import app.Repository.ComposedOperationLogic;
import app.Repository.MessageRepository;
import app.Repository.OperationRepository;
import app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@CrossOrigin
@Controller
@RequestMapping(path = "/messages")
public class MessageController {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ComposedOperationLogic composedOperationLogic;

    @Autowired
    public MessageController(MessageRepository messageRepository, UserRepository userRepository, ComposedOperationLogic composedOperationLogic) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.composedOperationLogic = composedOperationLogic;
    }

    @PostMapping(path="")
    public @ResponseBody String addMessage (@RequestBody Message message){

        /*if (message.getTitle().isEmpty() || message.getContent().isEmpty()){
            return "missing parameters";
        }*/

        long userId = message.getSender().getId();
        User user = userRepository.findOne(userId);

        if (user == null){
            return "User not found";
        }

        List<User> recipients = new LinkedList<>();
        for(User recipient : message.getRecipients()) {
            User dbRecipient = userRepository.findOne(recipient.getId());
            recipients.add(dbRecipient);
        }

        Message newMessage = new Message();
        newMessage.setTime(new Date());
        newMessage.setTitle(message.getTitle());
        newMessage.setContent(message.getContent());
        newMessage.setType(message.getType());
        newMessage.setSender(user);
        newMessage.setRecipients(recipients);
        newMessage.setType("UserMessage");
        messageRepository.save(newMessage);

        return "ok";
    }

    @PutMapping(path="/{id}")
    public @ResponseBody Message updateMessage (@PathVariable(value="id") long id, @RequestBody Message updatedMessage, @RequestHeader("User") Long user){
        Message message = messageRepository.findOne(id);

        if (message != null){
            message.setRead(updatedMessage.isRead());
            if (!message.isAcknowledged() && updatedMessage.isAcknowledged()) {
                if (message.getSendMessageOperation() != null) {
                    boolean result = composedOperationLogic.acknowledgeMessage(message.getSendMessageOperation(),user);
                    if (result) {
                        message.setAcknowledged(true);
                    }
                } else {
                    message.setAcknowledged(true);
                }

            }
            messageRepository.save(message);
            return message;
        } else {
            return null;
        }
    }

    @GetMapping(path = "")
    public @ResponseBody Iterable<Message> getAllMessages(){
        return this.messageRepository.findAll();
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody String deleteMessageById(@PathVariable(value="id") long id) {
        Message message = new Message();
        message = messageRepository.findOne(id);

        if(message != null){
            messageRepository.delete(message);
            return message.getTitle() + " is deleted";
        }

        return "Message cannot deleted!";
    }

    @GetMapping(path="/{id}")
    public @ResponseBody
    Message getMessageDetails (@PathVariable(value="id") long id) {
        Message message = messageRepository.findOne(id);
        message.setRead(true);
        messageRepository.save(message);
        return message;
    }


    @GetMapping(path="/forUser")
    public @ResponseBody
    List<Message> getMessagesForUser(@RequestHeader("User") Long user) {
        return StreamSupport.stream(messageRepository.findAll().spliterator(), false)
                .filter(m -> m.getRecipients().stream().anyMatch(r -> r.getId() == user))
                .collect(Collectors.toList());
    }


}
