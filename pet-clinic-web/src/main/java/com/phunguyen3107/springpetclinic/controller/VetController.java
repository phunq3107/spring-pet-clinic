package com.phunguyen3107.springpetclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/vet")
public class VetController {
    @GetMapping(path = {"","/","/index","/index.html"})
    public String listVets(){
        return "vet/index";
    }
}
