package com.financialtracker.vault.service;

import com.financialtracker.vault.model.Transfer;

import java.security.Principal;
import java.util.List;

public interface TransferService {
    static Transfer updateTransfer(Principal principal, Transfer transfer) {
    }

    List<Transfer> viewAllTransfers(Principal principal);

    List<Transfer> viewTransfersByIsRepeating(Principal principal, boolean isRepeating);

    Transfer createTransfer(Principal principal, Transfer newTransfer);

    void deleteTransfer(Principal principal, int transferId);
}
