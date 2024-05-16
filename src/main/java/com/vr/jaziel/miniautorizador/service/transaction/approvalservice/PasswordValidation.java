package com.vr.jaziel.miniautorizador.service.transaction.approvalservice;

import com.vr.jaziel.miniautorizador.dto.TransactionDto;
import com.vr.jaziel.miniautorizador.service.transaction.approvalservice.ApprovalException.ERROR_MESSAGE;
import com.vr.jaziel.model.CardModel;

public class PasswordValidation extends ApprovalChain {

    @Override
    public void handleApproval(TransactionDto transactionDto, CardModel cardModel) throws ApprovalException {
        if (cardModel.getSenha().equals(transactionDto.getSenhaCartao())) {
            super.flow(transactionDto, cardModel);
            return;
        }
        throw new ApprovalException(ERROR_MESSAGE.SENHA_INVALIDA);
    }
}
