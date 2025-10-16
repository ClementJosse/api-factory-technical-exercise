package com.apifactory.backend.repository;

import com.apifactory.backend.model.Contract;
import com.apifactory.backend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findByClientAndEndDateAfter(Client client, LocalDate date);
    List<Contract> findByClientAndUpdateDateAfter(Client client, LocalDate date);
}
