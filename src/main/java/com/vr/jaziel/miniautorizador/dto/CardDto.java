package com.vr.jaziel.miniautorizador.dto;

import com.vr.jaziel.model.CardModel;

import lombok.Data;

@Data
public class CardDto {
    private String numeroCartao;
    private String senha;

    public static CardDto fromCard(CardModel card) {
        CardDto cardDto = new CardDto();
        cardDto.setNumeroCartao(card.getNumeroCartao());
        cardDto.setSenha(card.getSenha());
        return cardDto;
    }

    public static CardModel toCard(CardDto cardDto) {
        CardModel card = new CardModel();
        card.setNumeroCartao(cardDto.getNumeroCartao());
        card.setSenha(cardDto.getSenha());
        return card;
    }
}
