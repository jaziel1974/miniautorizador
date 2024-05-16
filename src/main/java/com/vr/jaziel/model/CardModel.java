package com.vr.jaziel.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("cards")
public class CardModel {
    @Id
    private Long id;
    @Column("numero_cartao")
    private String numeroCartao;
    private String senha;
    private Double saldo;
}
