package com.vr.jaziel.miniautorizador.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vr.jaziel.miniautorizador.dto.CardDto;
import com.vr.jaziel.miniautorizador.dto.TransactionDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TransactionControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void createTransactionWithSuccess() {
        CardDto cardDto = new CardDto();
        cardDto.setNumeroCartao("123456705");
        cardDto.setSenha("1234");

        ResponseEntity<CardDto> response = testRestTemplate.exchange("/cartoes", HttpMethod.POST,
                new HttpEntity<>(cardDto), CardDto.class);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setNumeroCartao(cardDto.getNumeroCartao());
        transactionDto.setValor(100.0);

        ResponseEntity<String> response2 = testRestTemplate.exchange("/transacoes", HttpMethod.POST,
                new HttpEntity<>(transactionDto), String.class);
        assertEquals(response2.getStatusCode(), HttpStatus.CREATED);
    }
}
