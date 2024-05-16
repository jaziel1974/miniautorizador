package com.vr.jaziel.miniautorizador.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vr.jaziel.miniautorizador.dto.TransactionDto;

@RestController
@RequestMapping("/transacoes")
public class TransactionController {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody TransactionDto transactionDto) {
        //nothing to do for now
    }
}