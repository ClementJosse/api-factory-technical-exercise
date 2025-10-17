package com.apifactory.backend.service;

import com.apifactory.backend.model.Client;
import com.apifactory.backend.model.Contract;
import com.apifactory.backend.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public Contract createContract(Contract contract) {
        if (contract.getStartDate() == null) {
            contract.setStartDate(LocalDate.now());
        }
        return contractRepository.save(contract);
    }
    public Optional<Contract> getContractById(Long id) {
        return contractRepository.findById(id);
    }

    public Contract updateCost(Contract contract, Double newCost) {
        contract.setCostAmount(newCost);
        return contractRepository.save(contract);
    }

    public List<Contract> getActiveContracts(Client client) {
        return contractRepository.findByClientAndEndDateAfter(client, LocalDate.now());
    }

    public List<Contract> getActiveContractsFilteredByUpdateDate(Client client, LocalDate date) {
        return contractRepository.findByClientAndUpdateDateAfter(client, date);
    }

    public Double sumActiveContracts(Client client) {
        return contractRepository.findByClientAndEndDateAfter(client, LocalDate.now())
                .stream()
                .mapToDouble(c -> c.getCostAmount() != null ? c.getCostAmount() : 0)
                .sum();
    }
}
