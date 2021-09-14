package com.phunguyen3107.springpetclinic.service;

import com.phunguyen3107.springpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {
    public Owner findOwnerByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
