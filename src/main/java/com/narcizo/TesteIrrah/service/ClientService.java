package com.narcizo.TesteIrrah.service;

import com.narcizo.TesteIrrah.entity.Client;
import com.narcizo.TesteIrrah.entity.Message;
import com.narcizo.TesteIrrah.entity.PaymentPlan.PaymentPlan;
import com.narcizo.TesteIrrah.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

        return repository.save(client);
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

        return repository.save(existingClient);
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
}
