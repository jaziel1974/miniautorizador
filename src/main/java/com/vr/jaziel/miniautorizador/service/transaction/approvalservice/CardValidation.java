package com.vr.jaziel.miniautorizador.service.transaction.approvalservice;

import com.vr.jaziel.miniautorizador.dto.TransactionDto;
import com.vr.jaziel.miniautorizador.service.transaction.approvalservice.ApprovalException.ERROR_MESSAGE;
import com.vr.jaziel.model.CardModel;

public class CardValidation extends ApprovalChain {
    @Override
    public void handleApproval(TransactionDto transactionDto, CardModel cardModel) throws ApprovalException {
        if(cardModel != null) {
            super.flow(transactionDto, cardModel);
            return;
        }
        throw new ApprovalException(ERROR_MESSAGE.CARTAO_INEXISTENTE);
    }
}