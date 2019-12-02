package com.chicolira.clientservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chicolira.clientservice.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	Client findByName(String name);
}
