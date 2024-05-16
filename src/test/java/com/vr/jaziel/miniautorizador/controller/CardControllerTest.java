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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void createCardWithSuccess() {
        CardDto cardDto = new CardDto();
        cardDto.setNumeroCartao("123456701");
        cardDto.setSenha("1234");

        ResponseEntity<CardDto> response = testRestTemplate.exchange("/cartoes", HttpMethod.POST,
                new HttpEntity<>(cardDto), CardDto.class);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(cardDto.getNumeroCartao(), response.getBody().getNumeroCartao());
    }

    @Test
    void createCardWithFail_DuplicatedCard() {
        CardDto cardDto = new CardDto();
        cardDto.setNumeroCartao("123456702");
        cardDto.setSenha("1234");

        ResponseEntity<CardDto> response = testRestTemplate.exchange("/cartoes", HttpMethod.POST,
                new HttpEntity<>(cardDto), CardDto.class);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        
        ResponseEntity<CardDto> response2 = testRestTemplate.exchange("/cartoes", HttpMethod.POST,
                new HttpEntity<>(cardDto), CardDto.class);
        assertEquals(response2.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
        assertEquals(cardDto.getNumeroCartao(), response2.getBody().getNumeroCartao());
    }

    @Test
    void getBalanceWithSuccess() {
        Double balance = testRestTemplate.getForObject("/cartoes/123456703", Double.class);
        assertEquals(500.0, balance);
    }
}
