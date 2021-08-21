package com.phunguyen3107.springpetclinic.service.map;

import com.phunguyen3107.springpetclinic.model.Vet;
import com.phunguyen3107.springpetclinic.service.CrudService;

import java.util.Set;

public class VetServiceMap
        extends AbstractMapService<Vet, Long>
        implements CrudService<Vet, Long> {
    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void save(Vet object) {
        super.save(object.getId(), object);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
