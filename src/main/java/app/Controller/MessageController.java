package app.Controller;

import app.Model.Message;
import app.Model.User;
import app.Repository.MessageRepository;
import app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Controller
@RequestMapping(path = "/message")
public class MessageController {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository, UserRepository userRepository){
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }


    @PostMapping(path="/add")
    public @ResponseBody String addMessage (@RequestBody Message message){

        if (message.getTitle().isEmpty() || message.getContent().isEmpty()){
            return "missing parameters";
        }

        long userId = message.getTriggeredUser().getId();
        User user = userRepository.findOne(userId);

        if (user == null){
            return "User not found";
        }


        Message newMessage = new Message();
        newMessage.setTime(new Date());
        newMessage.setTitle(message.getTitle());
        newMessage.setContent(message.getContent());
        newMessage.setType(message.getType());
        newMessage.setTriggeredUser(user);
        messageRepository.save(newMessage);

        return "ok";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Message> getAllMessages(){
        return this.messageRepository.findAll();

    }
}
