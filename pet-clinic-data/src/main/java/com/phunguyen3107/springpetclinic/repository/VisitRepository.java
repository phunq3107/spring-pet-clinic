package com.phunguyen3107.springpetclinic.repository;

import com.phunguyen3107.springpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long > {
}
