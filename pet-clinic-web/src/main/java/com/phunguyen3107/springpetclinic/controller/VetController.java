package com.phunguyen3107.springpetclinic.controller;

import com.phunguyen3107.springpetclinic.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/vet")
public class VetController {
    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping(path = {"","/","/index","/index.html"})
    public String listVets(Model model ){
        model.addAttribute("listVets", vetService.findAll());
        return "vet/index";
    }
}
