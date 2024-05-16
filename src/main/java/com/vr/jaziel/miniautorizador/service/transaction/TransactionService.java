package com.vr.jaziel.miniautorizador.service.transaction;

import org.springframework.stereotype.Service;

import com.vr.jaziel.miniautorizador.dto.TransactionDto;
import com.vr.jaziel.miniautorizador.repository.CardRepository;
import com.vr.jaziel.model.CardModel;

@Service
public class TransactionService {
    private final CardRepository cardRepository;

    public TransactionService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void createTransaction(TransactionDto transactionDto) {
        CardModel cardModel = cardRepository.findByNumeroCartao(transactionDto.getNumeroCartao());
        cardModel.setSaldo(cardModel.getSaldo() - transactionDto.getValor());
        cardRepository.save(cardModel);
    }
}