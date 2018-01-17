package app.Controller;

import app.Model.Message;
import app.Model.User;
import app.Repository.MessageRepository;
import app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@CrossOrigin
@Controller
@RequestMapping(path = "/messages")
public class MessageController {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository, UserRepository userRepository){
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @PostMapping(path="")
    public @ResponseBody String addMessage (@RequestBody Message message){

        if (message.getTitle().isEmpty() || message.getContent().isEmpty()){
            return "missing parameters";
        }

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
        messageRepository.save(newMessage);

        return "ok";
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
        return messageRepository.findOne(id);
    }


    @GetMapping(path="/fromUser/{id}")
    public @ResponseBody
    List<Message> getMessagesFromUser(@PathVariable("id") long id) {
        Iterable<Message> allMessages = messageRepository.findAll();
        List<Message> filteredMessages = new ArrayList<>();
        for(Message message : allMessages){
            if(message.getSender().getId() == id){
                filteredMessages.add(message);
            }
        }
        return filteredMessages;
    }


}
