package com.financialtracker.vault.controller;


import com.financialtracker.vault.dao.TransferDao;
import com.financialtracker.vault.exception.ServiceException;
import com.financialtracker.vault.model.Account;


import com.financialtracker.vault.model.Transaction;
import com.financialtracker.vault.model.Transfer;
import com.financialtracker.vault.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated")
@CrossOrigin
public class TransferController {
    private final TransferService transferService;
    private final TransferDao transferDao;
    public TransferController(TransferService transferService, TransferDao transferDao) {
        this.transferService = transferService;
        this.transferDao = transferDao;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/transactions")
    public List<Transfer> getUserTransfers(@Valid Principal principal) {
        try{
            List<Transfer> transfers = transferService.viewAllTransfers(principal);
            return transfers;
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }

    }

    @GetMapping("/transfers/{transferId}")
    public Transfer getTransferById(@Valid Principal principal, @PathVariable("transferId") int transferId) {
        try {
            if (transfer == null) {
                throw new SecurityException("Transfer not found");
            }
            return transfer;
        }catch (SecurityException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }
    }


    @GetMapping("/transfers/{transferId}")
    public  List<Transfer>  getTransfersByIsRepeating(@Valid Principal principal, @PathVariable("boolean") boolean isRepeating ) {
        try{
            List<Transfer> transfers = transferService.viewTransfersByIsRepeating(principal, isRepeating);
            return transfers;
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }
    }

    @PostMapping("/transfers")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Transfer> sendTransfer(@Valid Principal principal, @RequestBody Transfer
            newTransfer){
        Transfer createdTransfer= null;
        try{
            createdTransfer = transferService.createTransfer(principal,newTransfer);
            if(createdTransfer == null){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return ResponseEntity.ok(createdTransfer);
            }
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("transfers/update/{transferId}")
    public Transfer updateTransfer(@Valid Principal principal, @RequestBody Transfer transfer, @PathVariable("transferId") int transferId) {
        transfer.setTransferId(transferId);
        try{
            Transfer updateTransfer = TransferService.updateTransfer(principal,transfer);
            if(updateTransfer == null){
                throw new ServiceException();
            }
            return updateTransfer;
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }
    }

    @PreAuthorize("isAuthenticated")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/transfers/{transferId}")
    public void deleteTransfer(@PathVariable int transferId, Principal principal) {
        try{
            transferService.deleteTransfer(principal, transferId );
        } catch (SecurityException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }
}
