package com.narcizo.TesteIrrah.service;

import com.narcizo.TesteIrrah.Utils.MyUtils;
import com.narcizo.TesteIrrah.entity.Client;
import com.narcizo.TesteIrrah.entity.Message;
import com.narcizo.TesteIrrah.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MessageService {
    @Autowired
    MessageRepository repository;

    @Autowired
    ClientService clientService;

    @Autowired
    MessageSenderService messageSenderservice;

    public List<Message> getMessageList(){
      return repository.findAll();
    }

    public List<Message> getMessagesFromClient(Long clientId){
        return this.getMessageList()
                .stream()
                .filter(
                        message -> message.getClient().getId() == clientId
                )
                .collect(Collectors.toList());
    }

    public Message sendMessage(Long clientId, Message message){
        Client client = clientService.checkIfClientObjectExists(clientId);

        if(!isMessageValid(message))
            return new Message();

        message.setSenderPhone(client.getPhone());
        message.setClient(client);

        return repository.save(this.actuallySendMessage(message, client));
    }

    public Message broadcastMessage(Long clientId, Message message){
        Client client = clientService.checkIfClientObjectExists(clientId);
        List<Message> messageList = new ArrayList<>();

        if(!isMessageValid(message))
            return new Message();

        message.setSenderPhone(client.getPhone());
        message.setClient(client);

        client.getUserPhoneNumbers().forEach(phone -> {
            Message newMessage = new Message(message);
            newMessage.setReceiverPhone(phone);
            messageList.add(actuallySendMessage(
                    newMessage,
                    client));
        });

        messageList.forEach(msg -> repository.save(msg));

        if(messageList.isEmpty())
            return message;

        return messageList.get(0);
    }

    public void deleteMessage(Long messageId){
        Message message = checkIfMessageObjectExists(messageId);

        repository.delete(message);
    }

    public void deleteAllMessages(){
        repository.findAll().forEach(
                message -> repository.delete(message)
        );
    }

    public Message checkIfMessageObjectExists(Long messageId){
        return repository.findById(messageId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Message not found with id: " +
                                messageId
                ));
    }

    private boolean isMessageValid(Message message){
        return (Objects.equals(message.getMessageType(), "sms") || Objects.equals(message.getMessageType(), "whatsapp"))
                && !MyUtils.validatePhoneNumber(message.getReceiverPhone()).isEmpty()
                && !Objects.equals(message.getTextMessage(), "");
    }

    private Message actuallySendMessage(Message message, Client client){
        if(Objects.equals(message.getMessageType(), "sms")){
            double balanceLeft = client.getPaymentPlan().usePlan(0.25);

            if(balanceLeft == -1)
                return new Message();

            client.addMessage(message);
            messageSenderservice.sendSms(message.getReceiverPhone(), message.getTextMessage());
            return message;

        }else if(Objects.equals(message.getMessageType(), "whatsapp")){
            client.addMessage(message);
            messageSenderservice.sendWhatsAppMessage(message.getReceiverPhone(), message.getTextMessage());
            return message;
        }

        return new Message();
    }
}
