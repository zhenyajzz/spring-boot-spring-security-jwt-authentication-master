package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Message;
import com.bezkoder.springjwt.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {

    public List<Message> findMessagesByProfile(Profile profile);

    public Message findMessagesById(Long id);


    public void deleteMessageById(Long id);
}
