package com.narcizo.TesteIrrah.controller;

import com.narcizo.TesteIrrah.entity.Message;
import com.narcizo.TesteIrrah.repository.MessageRepository;
import com.narcizo.TesteIrrah.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/message")
public class MessageController {
    @Autowired
    MessageRepository repository;
    @Autowired
    MessageService service;

    @GetMapping
    public List<Message> listAll (){
        System.out.println("List messages");
        return repository.findAll();
    }

}
