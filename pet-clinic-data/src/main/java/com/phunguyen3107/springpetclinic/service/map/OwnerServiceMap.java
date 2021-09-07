package com.phunguyen3107.springpetclinic.service.map;

import com.phunguyen3107.springpetclinic.model.Owner;
import com.phunguyen3107.springpetclinic.service.OwnerService;
import com.phunguyen3107.springpetclinic.service.PetService;
import com.phunguyen3107.springpetclinic.service.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerServiceMap
        extends AbstractMapService<Owner, Long>
        implements OwnerService {
    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void save(Owner object) {
        if(object!=null){
            if(object.getPets()!=null){
                object.getPets().forEach(pet ->{
                    if(pet.getPetType()!=null){
                        if(pet.getPetType().getId() == null){
                            petTypeService.save(pet.getPetType());
                            pet.setPetType(pet.getPetType());
                        }
                    }
                    else{
                        throw new RuntimeException("Pet type is required");
                    }
                });
            }
        }
        super.save(object.getId(), object);
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findOwnerByLastName(String lastName) {
        return null;
    }
}
