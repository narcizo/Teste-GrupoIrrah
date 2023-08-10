package com.narcizo.TesteIrrah.service;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class MessageSenderService {
    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    public void sendSms(String receiverPhoneNumber, String messageBody) {
        // COMENTADO POIS CONTÉM DADOS SENSÍVEIS
//        Twilio.init(accountSid, authToken);
//
//        Message.creator(
//                new PhoneNumber("+55"+receiverPhoneNumber),
//                new PhoneNumber("+17723205178"),
//                messageBody
//        ).create();
    }

    public void sendWhatsAppMessage(String receiverPhoneNumber, String messageBody) {
        // COMENTADO POIS CONTÉM DADOS SENSÍVEIS
//        receiverPhoneNumber = receiverPhoneNumber.replaceAll("[^\\d.]", "");
//        Twilio.init(accountSid, authToken);
//
//        Message.creator(
//                new com.twilio.type.PhoneNumber("whatsapp:+55" + receiverPhoneNumber),
//                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
//                messageBody
//        ).create();
    }
}
