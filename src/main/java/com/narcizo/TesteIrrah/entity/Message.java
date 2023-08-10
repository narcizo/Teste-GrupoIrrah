package com.narcizo.TesteIrrah.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String senderPhone;
    private String receiverPhone;
    private String messageType;
    private String textMessage;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    public Message(long id,
                   String senderPhone,
                   String receiverPhone,
                   String messageType,
                   String textMessage,
                   Client client
    ) {
        this.id = id;
        this.senderPhone = senderPhone;
        this.receiverPhone = receiverPhone;
        this.messageType = messageType;
        this.textMessage = textMessage;
        this.client = client;
    }

    public Message(Message anotherMessage){
        this.senderPhone = anotherMessage.getSenderPhone();
        this.receiverPhone = anotherMessage.getReceiverPhone();
        this.messageType = anotherMessage.getMessageType();
        this.textMessage = anotherMessage.getTextMessage();
        this.client = anotherMessage.getClient();
    }

    public Message() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
