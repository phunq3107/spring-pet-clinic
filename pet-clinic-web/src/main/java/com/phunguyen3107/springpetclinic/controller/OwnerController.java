package com.phunguyen3107.springpetclinic.controller;

import com.phunguyen3107.springpetclinic.model.Owner;
import com.phunguyen3107.springpetclinic.service.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(path = "/owners")
public class OwnerController {
    private static final String VIEW_OWNER_CREATE_OR_UPDATE_FORM = "owner/createOrUpdateOwnerForm";

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
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }
        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

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

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return VIEW_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return VIEW_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }

    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateForm(@PathVariable Long ownerId ,Model model) {
        Owner owner = ownerService.findById(ownerId);
        model.addAttribute("owner", owner);
        return VIEW_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateForm(@PathVariable Long ownerId,
                                    @Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return VIEW_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }

    }


}
