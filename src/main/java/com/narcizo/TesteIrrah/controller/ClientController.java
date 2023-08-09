package com.narcizo.TesteIrrah.controller;

import com.narcizo.TesteIrrah.entity.Client;
import com.narcizo.TesteIrrah.repository.ClientRepository;
import com.narcizo.TesteIrrah.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/client")
public class ClientController {
    @Autowired
    ClientRepository repository;
    @Autowired
    ClientService service;

    @GetMapping
    public List<Client> listAllClients () {
        System.out.println("List clients");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Client>> getClientById(@PathVariable Long id){
        Optional<Client> client = repository.findById(id);
        System.out.println("List single client");

        return ResponseEntity.ok(client);
    }

    @PostMapping
    public void saveClient (@RequestBody Client client) {
        repository.save(client);
        System.out.println("Save client");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Client>> updateClient(@PathVariable Long id, @RequestBody Client updatedClient){
        Optional<Client> client = repository.findById(id);

        client.ifPresent(c -> c.setName(updatedClient.getName()));
        client.ifPresent(c -> c.setEmail(updatedClient.getEmail()));
        client.ifPresent(c -> c.setPhone(updatedClient.getPhone()));
        client.ifPresent(c -> c.setCpf(updatedClient.getCpf()));
        client.ifPresent(c -> c.setCnpj(updatedClient.getCnpj()));
        client.ifPresent(c -> c.setCompanyName(updatedClient.getCompanyName()));

        client.ifPresent(c -> repository.save(c));

        System.out.println("Update client");
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id){
        Optional<Client> client = repository.findById(id);

        client.ifPresent(c -> repository.delete(c));
        System.out.println("Delete client");
    }

}
