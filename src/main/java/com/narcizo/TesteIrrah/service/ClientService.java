package com.narcizo.TesteIrrah.service;

import com.narcizo.TesteIrrah.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    ClientRepository repository;
}
