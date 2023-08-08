package com.narcizo.TesteIrrah.repository;

import com.narcizo.TesteIrrah.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
