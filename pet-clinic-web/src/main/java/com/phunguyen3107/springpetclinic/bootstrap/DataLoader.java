package com.phunguyen3107.springpetclinic.bootstrap;

import com.phunguyen3107.springpetclinic.model.*;
import com.phunguyen3107.springpetclinic.service.OwnerService;
import com.phunguyen3107.springpetclinic.service.PetTypeService;
import com.phunguyen3107.springpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;

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
    @Transactional
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
//        dog.setId(1L);
        dog.setName("Dog");
        petTypeService.save(dog);

        PetType cat = new PetType();
//        dog.setId(2L);
        cat.setName("Cat");
        petTypeService.save(cat);

        System.out.println("Loaded pet types...");


        Owner owner1 = new Owner();
//        owner1.setId(1L);
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("Address-of-owner-" + owner1.getFirstName());
        owner1.setCity("City-of-owner-" + owner1.getFirstName());
        owner1.setTelephone("Phone-number-of-owner-" + owner1.getFirstName());
        ownerService.save(owner1);

        Owner owner2 = new Owner();
//        owner2.setId(2L);
        owner2.setFirstName("John");
        owner2.setLastName("Wilson");
        owner2.setAddress("Address-of-owner-" + owner2.getFirstName());
        owner2.setCity("City-of-owner-" + owner2.getFirstName());
        owner2.setTelephone("Phone-number-of-owner-" + owner2.getFirstName());
        ownerService.save(owner2);

        Owner owner3 = new Owner();
//        owner2.setId(2L);
        owner3.setFirstName("John");
        owner3.setLastName("Wilson");
        owner3.setAddress("Address-of-owner-" + owner3.getFirstName());
        owner3.setCity("City-of-owner-" + owner3.getFirstName());
        owner3.setTelephone("Phone-number-of-owner-" + owner3.getFirstName());
        ownerService.save(owner3);

        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
//        vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
//        vet2.setId(2L);
        vet2.setFirstName("Steve");
        vet2.setLastName("Porter");
        vetService.save(vet2);
        System.out.println("Loaded vets...");

        Visit visit1 = Visit.builder()
                .date(LocalDate.now())
                .description("Nothing").build();

        Pet pet1 = Pet.builder()
                .name("Cat")
                .petType(cat)
                .owner(owner1)
                .visits(new HashSet<>())
                .birthDate(LocalDate.now()).build();
        pet1.getVisits().add(visit1);
        visit1.setPet(pet1);
        owner1.getPets().add(pet1);
        System.out.println("Loaded pet...");


    }
}
