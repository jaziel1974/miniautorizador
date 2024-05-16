package com.vr.jaziel.miniautorizador.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.vr.jaziel.miniautorizador.dto.CardDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void createCardWithSuccess(){
        CardDto cardDto = new CardDto();
        cardDto.setNumeroCartao("123456789");
        cardDto.setSenha("1234");

        CardDto response = testRestTemplate.postForObject("/cartoes", cardDto, CardDto.class);
        assertEquals(cardDto.getNumeroCartao(), response.getNumeroCartao());
    }

    @Test
    void getBalanceWithSuccess(){
        Double balance = testRestTemplate.getForObject("/cartoes/123456789", Double.class);
        assertEquals(500.0, balance);
    }
}
