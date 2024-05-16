package com.vr.jaziel.miniautorizador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vr.jaziel.miniautorizador.dto.TransactionDto;
import com.vr.jaziel.miniautorizador.service.transaction.TransactionService;
import com.vr.jaziel.miniautorizador.service.transaction.approvalservice.ApprovalException;
import com.vr.jaziel.miniautorizador.service.transaction.approvalservice.ApprovalService;

@RestController
@RequestMapping("/transacoes")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ApprovalService approvalService;

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody TransactionDto transactionDto) {
        try {
            approvalService.approveTransaction(transactionDto);
            transactionService.createTransaction(transactionDto);
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
        } catch (ApprovalException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(422)).body(e.getMessage());
        }
    }
}