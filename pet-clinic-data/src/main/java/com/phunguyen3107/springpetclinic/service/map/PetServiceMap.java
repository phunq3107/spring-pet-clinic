package com.phunguyen3107.springpetclinic.service.map;

import com.phunguyen3107.springpetclinic.model.Pet;
import com.phunguyen3107.springpetclinic.service.PetService;

import java.util.Set;

public class PetServiceMap
        extends AbstractMapService<Pet, Long>
        implements PetService {
    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public void save(Pet object) {
        super.save(object.getId(), object);
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }
}
