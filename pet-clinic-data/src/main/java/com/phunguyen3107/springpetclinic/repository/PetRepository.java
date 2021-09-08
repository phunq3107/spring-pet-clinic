package com.phunguyen3107.springpetclinic.repository;

import com.phunguyen3107.springpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
