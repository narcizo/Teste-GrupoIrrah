package com.narcizo.TesteIrrah.controller;

import com.narcizo.TesteIrrah.entity.Message;
import com.narcizo.TesteIrrah.repository.MessageRepository;
import com.narcizo.TesteIrrah.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/message")
public class MessageController {
    @Autowired
    MessageRepository repository;
    @Autowired
    MessageService service;

    @GetMapping
    public List<Message> listAllMessages () throws IOException {
        System.out.println("List messages");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Message>> getMessageById(@PathVariable Long id){
        Optional<Message> message = repository.findById(id);
        System.out.println("List single message");

        return ResponseEntity.ok(message);
    }

    @PostMapping
    public void saveMessage (@RequestBody Message message) throws IOException {
        repository.save(message);
        System.out.println("Save message");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Message>> updateMessage(@PathVariable Long id, @RequestBody Message updatedMessage){
        Optional<Message> message = repository.findById(id);

        message.ifPresent(m -> m.setSenderPhone(updatedMessage.getSenderPhone()));
        message.ifPresent(m -> m.setReceiverPhone(updatedMessage.getReceiverPhone()));
        message.ifPresent(m -> m.setMessageType(updatedMessage.getMessageType()));
        message.ifPresent(m -> m.setTextMessage(updatedMessage.getTextMessage()));

        message.ifPresent(c -> repository.save(c));

        System.out.println("Update message");
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id){
        Optional<Message> message = repository.findById(id);

        message.ifPresent(m -> repository.delete(m));
        System.out.println("Delete message");
    }
}
