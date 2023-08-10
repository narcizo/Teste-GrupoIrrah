package com.narcizo.TesteIrrah.controller;

import com.narcizo.TesteIrrah.entity.Message;
import com.narcizo.TesteIrrah.repository.MessageRepository;
import com.narcizo.TesteIrrah.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/client/{clientId}")
    public ResponseEntity<Message> getMessagesClient(@PathVariable Long id){
        //TODO
        Message message = service.getMessage(id);
        System.out.println("clientId");

        return ResponseEntity.ok(message);
    }

    @PostMapping("/send-message/{clientId}")
    public ResponseEntity<Message> sendMessage(@PathVariable Long clientId, @RequestBody Message message){
        Message sentmessage = service.sendMessage(clientId, message);

        if(sentmessage.getId() == 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sentmessage);
        return ResponseEntity.ok(sentmessage);
    }

    @PostMapping("/broadcast-message/{clientId}")
    public ResponseEntity<Message> broadcastMessage(@PathVariable Long clientId, @RequestBody Message message){
        Message sentmessage = service.broadcastMessage(clientId, message);

        if(sentmessage.getId() == 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sentmessage);
        return ResponseEntity.ok(sentmessage);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id){
        service.deleteMessage(id);
    }

    @DeleteMapping("/delete-all")
    public void deleteAllMessages(){
        service.deleteAllMessages();
    }
}
