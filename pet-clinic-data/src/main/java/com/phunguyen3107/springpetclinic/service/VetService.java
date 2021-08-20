package com.phunguyen3107.springpetclinic.service;

import com.phunguyen3107.springpetclinic.model.Vet;

import java.util.Set;

public interface VetService {
    Vet findById(Long id);

    Vet save(Vet owner);

    Set<Vet> findAll();
}
