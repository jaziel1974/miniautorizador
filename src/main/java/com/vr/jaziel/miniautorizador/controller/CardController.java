package com.vr.jaziel.miniautorizador.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vr.jaziel.miniautorizador.dto.CardDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/cartoes")
public class CardController {
    
    @PostMapping()
    public CardDto create(@RequestBody CardDto cardDto) {
        CardDto savedCard = new CardDto();
        savedCard.setNumeroCartao(cardDto.getNumeroCartao());
        savedCard.setSenha(cardDto.getSenha());
        return savedCard;
    }

    @GetMapping("/{numeroCartao}")
    public Double getBalance(@PathVariable String numeroCartao) {
        return 500.0;
    }
}
