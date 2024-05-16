package com.vr.jaziel.miniautorizador.service.transaction.approvalservice;

import com.vr.jaziel.miniautorizador.dto.TransactionDto;
import com.vr.jaziel.model.CardModel;

public abstract class ApprovalChain {
    protected ApprovalChain successor;

    public void setSuccessor(ApprovalChain successor) {
        this.successor = successor;
    }

    public abstract void handleApproval(TransactionDto transactionDto, CardModel cardModel) throws ApprovalException;

    public void flow(TransactionDto transactionDto, CardModel cardModel) throws ApprovalException {
        if (successor != null) successor.handleApproval(transactionDto, cardModel);
    }
}