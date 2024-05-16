package com.vr.jaziel.miniautorizador.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.vr.jaziel.miniautorizador.dto.CardDto;
import com.vr.jaziel.miniautorizador.service.CardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cartoes")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping()
    public ResponseEntity<CardDto> create(@RequestBody CardDto cardDto) {
        try {
            CardDto savedCard = cardService.createCard(CardDto.toCard(cardDto));
            return new ResponseEntity<CardDto>(savedCard, HttpStatus.CREATED);
        } catch (Exception e) {
            if (e.getCause() instanceof DuplicateKeyException err) {
                ResponseEntity<CardDto> response = new ResponseEntity<CardDto>(cardDto,
                        HttpStatus.UNPROCESSABLE_ENTITY);
                return response;
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping("/{numeroCartao}")
    public Double getBalance(@PathVariable String numeroCartao) {
        return 500.0;
    }}