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
        transactionDto.setSenhaCartao(cardDto.getSenha());
        transactionDto.setValor(100.0);

        ResponseEntity<String> response2 = testRestTemplate.exchange("/transacoes", HttpMethod.POST,
                new HttpEntity<>(transactionDto), String.class);
        assertEquals(response2.getStatusCode(), HttpStatus.CREATED);

        ResponseEntity<Double> balance = testRestTemplate.exchange("/cartoes/" + cardDto.getNumeroCartao(),
                HttpMethod.GET, null, Double.class);
        assertEquals(balance.getStatusCode(), HttpStatus.OK);
        assertEquals(400.0, balance.getBody());
    }

    @Test
    void createTransactionWithFail_CardNotExists() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setNumeroCartao("123456706");

        ResponseEntity<String> response2 = testRestTemplate.exchange("/transacoes", HttpMethod.POST,
                new HttpEntity<>(transactionDto), String.class);
        assertEquals(response2.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
        assertEquals(response2.getBody(), "CARTAO_INEXISTENTE");
    }

    @Test
    void createTransactionWithFail_WrongPassword() {
        CardDto cardDto = new CardDto();
        cardDto.setNumeroCartao("123456707");
        cardDto.setSenha("1234");

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setNumeroCartao(cardDto.getNumeroCartao());
        transactionDto.setSenhaCartao("12345");

        ResponseEntity<CardDto> response = testRestTemplate.exchange("/cartoes", HttpMethod.POST,
                new HttpEntity<>(cardDto), CardDto.class);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        ResponseEntity<String> response2 = testRestTemplate.exchange("/transacoes", HttpMethod.POST,
                new HttpEntity<>(transactionDto), String.class);
        assertEquals(response2.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
        assertEquals(response2.getBody(), "SENHA_INVALIDA");
    }

    @Test
    void createTransactionWithFail_BalanceNotEnough() {
        CardDto cardDto = new CardDto();
        cardDto.setNumeroCartao("123456708");
        cardDto.setSenha("1234");

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setNumeroCartao(cardDto.getNumeroCartao());
        transactionDto.setSenhaCartao("1234");
        transactionDto.setValor(501.0);

        ResponseEntity<CardDto> response = testRestTemplate.exchange("/cartoes", HttpMethod.POST,
                new HttpEntity<>(cardDto), CardDto.class);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        ResponseEntity<String> response2 = testRestTemplate.exchange("/transacoes", HttpMethod.POST,
                new HttpEntity<>(transactionDto), String.class);
        assertEquals(response2.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
        assertEquals(response2.getBody(), "SALDO_INSUFICIENTE");
    }

    @Test
    void createTransactionWithFail_BalanceNotEnough_SeveralWithdraws() {
        CardDto cardDto = new CardDto();
        cardDto.setNumeroCartao("123456709");
        cardDto.setSenha("1234");

        ResponseEntity<CardDto> response = testRestTemplate.exchange("/cartoes", HttpMethod.POST,
                new HttpEntity<>(cardDto), CardDto.class);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setNumeroCartao(cardDto.getNumeroCartao());
        transactionDto.setSenhaCartao("1234");
        transactionDto.setValor(400.0);
        ResponseEntity<String> response2 = testRestTemplate.exchange("/transacoes", HttpMethod.POST,
                new HttpEntity<>(transactionDto), String.class);
        assertEquals(response2.getStatusCode(), HttpStatus.CREATED);

        transactionDto.setValor(100.0);
        ResponseEntity<String> response3 = testRestTemplate.exchange("/transacoes", HttpMethod.POST,
                new HttpEntity<>(transactionDto), String.class);
        assertEquals(response3.getStatusCode(), HttpStatus.CREATED);

        transactionDto.setValor(1.0);
        ResponseEntity<String> response4 = testRestTemplate.exchange("/transacoes", HttpMethod.POST,
                new HttpEntity<>(transactionDto), String.class);
        assertEquals(response4.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
        assertEquals(response4.getBody(), "SALDO_INSUFICIENTE");
    }
}