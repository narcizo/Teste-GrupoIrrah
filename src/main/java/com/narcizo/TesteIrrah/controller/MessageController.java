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
    MessageService service;

    @GetMapping
    public List<Message> getMessageList () {
        return service.getMessageList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable Long id){
        Message message = service.getMessage(id);

        return ResponseEntity.ok(message);
    }

//    @PostMapping
//    public ResponseEntity<Message> createMessage (@RequestBody Message message) {
//        Message created = service.createMessage(message);
//
//        return ResponseEntity.ok(created);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody Message updatedMessage){
        Message updated = service.updateMessage(id, updatedMessage);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id){
        service.deleteMessage(id);
    }

    @PostMapping("/send-message/{clientId}")
    public ResponseEntity<Message> sendMessage(@PathVariable Long clientId, @RequestBody Message message){
        Message sentmessage = service.sendMessage(clientId, message);
        return ResponseEntity.ok(sentmessage);
    }

    @PostMapping("/broadcast-message/{clientId}")
    public ResponseEntity<Message> broadcastMessage(@PathVariable Long clientId, @RequestBody Message message){
        Message sentmessage = service.broadcastMessage(clientId, message);
        return ResponseEntity.ok(sentmessage);
    }
}
