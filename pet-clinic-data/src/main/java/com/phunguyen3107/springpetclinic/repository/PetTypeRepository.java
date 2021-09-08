package com.phunguyen3107.springpetclinic.repository;

import com.phunguyen3107.springpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
