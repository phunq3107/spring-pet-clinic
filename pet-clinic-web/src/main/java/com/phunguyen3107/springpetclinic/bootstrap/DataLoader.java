package com.phunguyen3107.springpetclinic.bootstrap;

import com.phunguyen3107.springpetclinic.model.Owner;
import com.phunguyen3107.springpetclinic.model.PetType;
import com.phunguyen3107.springpetclinic.model.Vet;
import com.phunguyen3107.springpetclinic.service.OwnerService;
import com.phunguyen3107.springpetclinic.service.PetTypeService;
import com.phunguyen3107.springpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setId(1L);
        dog.setName("Dog");
        petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setId(2L);
        dog.setName("Cat");
        petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("John");
        owner2.setLastName("Wilson");
        ownerService.save(owner2);

        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Steve");
        vet2.setLastName("Porter");
        vetService.save(vet2);
        System.out.println("Loaded vets...");


    }
}
