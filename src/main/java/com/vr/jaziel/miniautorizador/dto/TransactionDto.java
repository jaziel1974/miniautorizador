package com.vr.jaziel.miniautorizador.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private String numeroCartao;
    private String senhaCartao;
    private Double valor;
}
