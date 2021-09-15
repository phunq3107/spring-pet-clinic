package com.phunguyen3107.springpetclinic.formatter;

import com.phunguyen3107.springpetclinic.model.PetType;
import com.phunguyen3107.springpetclinic.service.PetTypeService;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {
    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public String print(PetType object, Locale locale) {
        return object.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) {
        Collection<PetType> petTypes = petTypeService.findAll();
        for (PetType petType : petTypes) {
            if (petType.getName().equals(text)) {
                return petType;
            }
        }
        throw new ParseException(0, "type not found: " + text);
    }
}
