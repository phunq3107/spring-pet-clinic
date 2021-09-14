package com.phunguyen3107.springpetclinic.controller;

import com.phunguyen3107.springpetclinic.model.Owner;
import com.phunguyen3107.springpetclinic.service.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;


@Controller
@RequestMapping(path = "/owners")
public class OwnerController {
    private final OwnerService ownerService;

    @InitBinder
    public void setAllowedFiles(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");

    }

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

//    @GetMapping(path = {"", "/", "/index", "/index.html"})
    public String listOwners(Model model) {
        model.addAttribute("listOwners", ownerService.findAll());
        return "owner/index";
    }

    @GetMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owner/findOwners";

    }

    @GetMapping("")
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        System.out.println(owner.getFirstName() + owner.getId());
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }
        List<Owner> results = ownerService.findAllByLastNameLike(owner.getLastName());

        if (results.isEmpty()) {
            result.rejectValue("lastName", "notFound", "notFound");
            return "owner/findOwners";
        } else if (results.size() == 1) {
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            model.addAttribute("selections", results);
            return "owner/ownersList";
        }

    }

    @GetMapping("/{ownerId}")
    public String showOwner(@PathVariable Long ownerId, Model model) {
        model.addAttribute("owner", ownerService.findById(ownerId));
        return "owner/ownerDetails";
    }
}
