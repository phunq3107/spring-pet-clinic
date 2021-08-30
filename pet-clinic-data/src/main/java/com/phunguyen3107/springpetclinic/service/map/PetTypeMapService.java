package com.phunguyen3107.springpetclinic.service.map;

import com.phunguyen3107.springpetclinic.model.PetType;
import com.phunguyen3107.springpetclinic.service.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {
    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(PetType object) {
        super.delete(object);
    }

    @Override
    public void save(PetType object) {
        super.save(object.getId(), object);
    }

    @Override
    public PetType findById(Long id) {
        return super.findById(id);
    }
}
