package com.vr.jaziel.miniautorizador.service;

import org.springframework.stereotype.Service;

import com.vr.jaziel.miniautorizador.dto.CardDto;
import com.vr.jaziel.miniautorizador.repository.CardRepository;
import com.vr.jaziel.model.CardModel;

@Service
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public CardDto createCard(CardModel card) {
        card.setSaldo(500.0);
        return CardDto.fromCard(cardRepository.save(card));
    }

    public Double getBalance(String numeroCartao) {
        return cardRepository.getBalance(numeroCartao);
    }
}
