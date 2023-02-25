package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Message;
import com.bezkoder.springjwt.models.Profile;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.MessageRepository;
import com.bezkoder.springjwt.repository.ProfileRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileRepository profileRepository;


    public Profile findProfile(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();

        Profile profile = profileRepository.findProfileByUser(user);

        return profile;
    }

    @GetMapping("/all")
    public List<Message> messageList(Principal principal) {
        Profile profile = findProfile(principal);
        List<Message> message = messageRepository.findMessagesByProfile(profile);
        return message;
    }

    @PostMapping
    public Message createMessage(@RequestBody Message message, Principal principal) {

        Profile profile = findProfile(principal);
        message.setProfile(profile);
        Message message1 = messageRepository.save(message);
//
//        profile.addMessageToList(message1);
//        profileRepository.save(profile);
        return message1;
    }



    @GetMapping("/{id}")
    public Message findMessageById(@PathVariable Long id){
        return messageRepository.findMessagesById(id);
    }

    @PutMapping("/{id}")
    public Message changeMessage(@PathVariable Long id, @RequestBody Message message){

        Message findMessage = messageRepository.findMessagesById(id);

        findMessage.setText(message.getText());

        return messageRepository.save(findMessage);
    }

    @DeleteMapping("/{id}")
    public void removeMessageById(@PathVariable Long id){

         messageRepository.deleteMessageById(id);

    }
}
