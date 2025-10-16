package com.apifactory.backend.service;

import com.apifactory.backend.model.Client;
import com.apifactory.backend.repository.ClientRepository;
import com.apifactory.backend.model.Contract;
import com.apifactory.backend.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ContractRepository contractRepository;

    public ClientService(ClientRepository clientRepository, ContractRepository contractRepository) {
        this.clientRepository = clientRepository;
        this.contractRepository = contractRepository;
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> getClient(Long id) {
        return clientRepository.findById(id);
    }

    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.findById(id).ifPresent(client -> {
            LocalDate now = LocalDate.now();
            List<Contract> contracts = client.getContracts();
            if (contracts != null) {
                contracts.forEach(c -> {
                    if (c.getEndDate() == null || c.getEndDate().isAfter(now)) {
                        c.setEndDate(now);
                        contractRepository.save(c);
                    }
                });
            }
            clientRepository.delete(client);
        });
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
