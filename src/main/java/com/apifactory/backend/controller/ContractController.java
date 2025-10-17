package com.apifactory.backend.controller;

import com.apifactory.backend.model.Client;
import com.apifactory.backend.model.Contract;
import com.apifactory.backend.service.ClientService;
import com.apifactory.backend.service.ContractService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    private final ContractService contractService;
    private final ClientService clientService;

    public ContractController(ContractService contractService, ClientService clientService) {
        this.contractService = contractService;
        this.clientService = clientService;
    }

    @PostMapping("/client/{clientId}")
    public ResponseEntity<Contract> createContract(@PathVariable Long clientId, @RequestBody Contract contract) {
        return clientService.getClient(clientId).map(client -> {
            contract.setClient(client);
            return ResponseEntity.ok(contractService.createContract(contract));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/cost")
    public ResponseEntity<Contract> updateCost(@PathVariable Long id, @RequestParam Double cost) {
        return contractService.getContractById(id)
                .map(contract -> ResponseEntity.ok(contractService.updateCost(contract, cost)))
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Contract>> getContracts(
            @PathVariable Long clientId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate updatedAfter) {
        return clientService.getClient(clientId).map(client -> {
            List<Contract> contracts;
            if (updatedAfter != null) {
                contracts = contractService.getActiveContractsFilteredByUpdateDate(client, updatedAfter);
            } else {
                contracts = contractService.getActiveContracts(client);
            }
            return ResponseEntity.ok(contracts);
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{clientId}/sum")
    public ResponseEntity<Double> sumContracts(@PathVariable Long clientId) {
        return clientService.getClient(clientId)
                .map(client -> ResponseEntity.ok(contractService.sumActiveContracts(client)))
                .orElse(ResponseEntity.notFound().build());
    }
}
