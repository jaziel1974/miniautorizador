package com.vr.jaziel.miniautorizador.repository;

import org.springframework.data.repository.CrudRepository;

import com.vr.jaziel.model.CardModel;

public interface CardRepository extends CrudRepository<CardModel, Long> {
 }
