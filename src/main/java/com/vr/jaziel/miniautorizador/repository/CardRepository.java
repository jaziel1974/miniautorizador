package com.vr.jaziel.miniautorizador.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vr.jaziel.model.CardModel;

public interface CardRepository extends CrudRepository<CardModel, Long> {

    @Query("select saldo from cards where numero_cartao = :numeroCartao")
    Double getBalance(@Param("numeroCartao") String numeroCartao);
 }
