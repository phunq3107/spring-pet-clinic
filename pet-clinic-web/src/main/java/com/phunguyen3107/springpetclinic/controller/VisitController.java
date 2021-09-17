package com.phunguyen3107.springpetclinic.controller;

import com.phunguyen3107.springpetclinic.model.Pet;
import com.phunguyen3107.springpetclinic.model.Visit;
import com.phunguyen3107.springpetclinic.service.PetService;
import com.phunguyen3107.springpetclinic.service.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class VisitController {
    private final VisitService visitService;

    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable("petId") Long petId, Model model) {
        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        return visit;
    }

    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable("petId") Long petId, Model model) {
        return "pet/createOrUpdateVisitForm";
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit, @PathVariable Long ownerId, Pet pet, BindingResult result) {
        if (result.hasErrors()) {
            return "pet/createOrUpdateVisitForm";
        } else {
            pet.getVisits().add(visit);
            visit.setPet(pet);
            visitService.save(visit);
            return "redirect:/owners/" + ownerId;
        }
    }
}
