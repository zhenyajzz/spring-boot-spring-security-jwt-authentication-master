package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Message> messageList;

    public boolean addMessageToList(Message message){
        return messageList.add(message);
    }

    public boolean removeMessageFromList(Message message){
        return messageList.remove(message);
    }



    public Profile() {
    }

    public Profile(Long id, User user, List<Message> messageList) {
        this.id = id;
        this.user = user;
        this.messageList = messageList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
