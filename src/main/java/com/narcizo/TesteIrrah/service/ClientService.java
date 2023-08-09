package com.narcizo.TesteIrrah.service;

import com.narcizo.TesteIrrah.entity.Client;
import com.narcizo.TesteIrrah.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientRepository repository;

    public Client getCLient(Long clientId){
        return repository.findById(clientId).
                orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));
    }

    public List<Client> getClientList(){
        return repository.findAll();
    }

    public Client createClient(Client client){
        return repository.save(client);
    }

    public Client updateClient(Long clientId, Client updatedClient) {
        Client existingClient = repository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));

        existingClient.setName(updatedClient.getName());
        existingClient.setEmail(updatedClient.getEmail());
        existingClient.setPhone(updatedClient.getPhone());
        existingClient.setCpf(updatedClient.getCpf());
        existingClient.setCnpj(updatedClient.getCnpj());
        existingClient.setCompanyName(updatedClient.getCompanyName());

        return repository.save(existingClient);
    }

    public void deleteClient(Long clientId){
        Client client = this.getCLient(clientId);

        repository.delete(client);
    }
}
