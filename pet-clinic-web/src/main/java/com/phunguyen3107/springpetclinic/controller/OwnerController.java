package com.phunguyen3107.springpetclinic.controller;

import com.phunguyen3107.springpetclinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/owner")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping(path = {"", "/", "/index", "/index.html"})
    public String listOwners(Model  model) {
        model.addAttribute("listOwners", ownerService.findAll());
        return "owner/index";
    }
}
