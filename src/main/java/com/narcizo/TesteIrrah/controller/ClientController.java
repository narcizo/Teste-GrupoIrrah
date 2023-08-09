package com.narcizo.TesteIrrah.controller;

import com.narcizo.TesteIrrah.entity.Client;
import com.narcizo.TesteIrrah.repository.ClientRepository;
import com.narcizo.TesteIrrah.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/client")
public class ClientController {
    @Autowired
    ClientService service;

    @GetMapping
    public List<Client> getClientList () {
        return service.getClientList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getCLient(@PathVariable Long id){
        Client client = service.getCLient(id);

        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Client> createClient (@RequestBody Client client) {
        Client created = service.createClient(client);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updatedClient){
        Client updated = service.updateClient(id, updatedClient);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id){
        service.deleteClient(id);
    }

}
