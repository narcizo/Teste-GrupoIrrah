package com.narcizo.TesteIrrah.service;

import com.narcizo.TesteIrrah.Utils.MyUtils;
import com.narcizo.TesteIrrah.entity.Client;
import com.narcizo.TesteIrrah.entity.Message;
import com.narcizo.TesteIrrah.entity.PaymentPlan.PaymentPlan;
import com.narcizo.TesteIrrah.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    ClientRepository repository;

    @Autowired
    PaymentPlanService paymentPlanService;

    public Client getCLient(Long clientId){
        return checkIfClientObjectExists(clientId);
    }

    public List<Client> getClientList(){
        return repository.findAll();
    }

    public Client createClient(Client client){
        Long paymentPlanId = client.getPaymentPlan().getId();

        PaymentPlan paymentPlan =  paymentPlanService.checkIfPaymentPlanObjectExists(paymentPlanId);

        if(this.isPaymentPlanValid(paymentPlan, client))
            client.setPaymentPlan(paymentPlan);

        client.setUserPhoneNumbers(this.validateUsersPhoneNumbers(client.getUserPhoneNumbers()));

        if(this.isClientValid(client))
            return repository.save(client);

        return new Client();
    }

    public Client updateClient(Long clientId, Client updatedClient) {
        Client existingClient = checkIfClientObjectExists(clientId);

        Long paymentPlanId = existingClient.getPaymentPlan().getId();

        PaymentPlan paymentPlan =  paymentPlanService.checkIfPaymentPlanObjectExists(paymentPlanId);

        existingClient.setName(updatedClient.getName());
        existingClient.setEmail(updatedClient.getEmail());
        existingClient.setPhone(updatedClient.getPhone());
        existingClient.setCpf(updatedClient.getCpf());
        existingClient.setCnpj(updatedClient.getCnpj());
        existingClient.setCompanyName(updatedClient.getCompanyName());
        if(this.isPaymentPlanValid(paymentPlan, existingClient))
            existingClient.setPaymentPlan(paymentPlan);
        else
            existingClient.setPaymentPlan(null);
        existingClient.setUserPhoneNumbers(
                this.validateUsersPhoneNumbers(
                        updatedClient.getUserPhoneNumbers()
                )
        );

        if(this.isClientValid(existingClient))
            return repository.save(existingClient);

        return new Client();
    }

    public void deleteClient(Long clientId){
        Client client = this.checkIfClientObjectExists(clientId);

        repository.delete(client);
    }

    public Client checkIfClientObjectExists(Long clientId){
        return repository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Client not found with id: " +
                                clientId
                ));
    }

    private boolean isPaymentPlanValid(PaymentPlan paymentPlan, Client client){
        return Objects.equals(paymentPlan.getPlanType(), client.getPaymentPlan().getPlanType());
    }

    private boolean isClientValid(Client client){
        if (MyUtils.validatePhoneNumber(client.getPhone()).isEmpty() ||
                MyUtils.validateCpf(client.getCpf()).isEmpty() ||
                MyUtils.validateCnpj(client.getCnpj()).isEmpty())
            return false;
        if (client.getName().isEmpty() ||
                client.getEmail().isEmpty() ||
                client.getCompanyName().isEmpty())
            return false;

        return true;
    }

    private List<String> validateUsersPhoneNumbers(List<String> phoneList){
        return phoneList
                .stream()
                .filter(phone -> !MyUtils.validatePhoneNumber(phone).isEmpty())
                .collect(Collectors.toList());
    }
}
