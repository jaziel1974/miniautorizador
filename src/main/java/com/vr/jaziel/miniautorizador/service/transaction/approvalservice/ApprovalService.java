package com.vr.jaziel.miniautorizador.service.transaction.approvalservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vr.jaziel.miniautorizador.dto.TransactionDto;
import com.vr.jaziel.miniautorizador.repository.CardRepository;
import com.vr.jaziel.model.CardModel;

@Service
public class ApprovalService {
    @Autowired
    private CardRepository cardRepository;

    public void approveTransaction(TransactionDto transactionDto) throws ApprovalException {
        CardModel cardModel = cardRepository.findByNumeroCartao(transactionDto.getNumeroCartao());

        ApprovalChain cardValidation = new CardValidation();
        ApprovalChain passwordValidation = new PasswordValidation();
        ApprovalChain balanceValidation = new BalanceValidation();
        cardValidation.setSuccessor(passwordValidation);
        passwordValidation.setSuccessor(balanceValidation);
        cardValidation.handleApproval(transactionDto, cardModel);
    }
}