package com.phunguyen3107.springpetclinic.controller;

import com.phunguyen3107.springpetclinic.model.Vet;
import com.phunguyen3107.springpetclinic.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
public class VetController {
    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping(path = {"/vets","/vets/","/vets/index","/vets/index.html"})
    public String listVets(Model model ){
        model.addAttribute("vets", vetService.findAll());
        return "vet/vetList";
    }

    @GetMapping({"/api/vets"})
    public @ResponseBody Set<Vet> getVetsJson(){
        return vetService.findAll();
    }
}
