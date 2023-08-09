package com.narcizo.TesteIrrah.service;

import com.narcizo.TesteIrrah.entity.Client;
import com.narcizo.TesteIrrah.entity.Message;
import com.narcizo.TesteIrrah.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    MessageRepository repository;

    public List<Message> getMessageList(){
      return repository.findAll();
    }

    public Message getMessage(Long messageId){
        return checkIfMessageObjectExists(messageId);
    }

    public Message createMessage(Message message){
        return repository.save(message);
    }

    public Message updateMessage(Long messageId, Message updatedMessage){
        Message existingMessage = checkIfMessageObjectExists(messageId);

        existingMessage.setSenderPhone(updatedMessage.getSenderPhone());
        existingMessage.setReceiverPhone(updatedMessage.getReceiverPhone());
        existingMessage.setMessageType(updatedMessage.getMessageType());
        existingMessage.setTextMessage(updatedMessage.getTextMessage());

        return repository.save(existingMessage);
    }

    public void deleteMessage(Long messageId){
        Message message = checkIfMessageObjectExists(messageId);

        repository.delete(message);
    }

    public Message checkIfMessageObjectExists(Long messageId){
        return repository.findById(messageId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Message not found with id: " +
                                messageId
                ));
    }
}
