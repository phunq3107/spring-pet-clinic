package com.phunguyen3107.springpetclinic.service;

import com.phunguyen3107.springpetclinic.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);

    Pet save(Pet owner);

    Set<Pet> findAll();
}
