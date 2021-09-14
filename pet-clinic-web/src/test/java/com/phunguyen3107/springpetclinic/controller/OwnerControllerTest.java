package com.phunguyen3107.springpetclinic.controller;

import com.phunguyen3107.springpetclinic.model.Owner;
import com.phunguyen3107.springpetclinic.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    Set<Owner> owners;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller).build();
    }

    @Test
    void listOwners() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners"))
                .andExpect(status().is(200))
                .andExpect(view().name("owner/index"))
                .andExpect(model().attribute("listOwners", hasSize(2)));
    }

    @Test
    void listOwners_byIndex() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners/index"))
                .andExpect(status().is(200))
                .andExpect(view().name("owner/index"))
                .andExpect(model().attribute("listOwners", hasSize(2)));
    }

    @Test
    void displayOwner() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(get("/owners/1"))
                .andExpect(status().is(200))
                .andExpect(view().name("owner/ownerDetails"))
                .andExpect(model().attribute("owner", notNullValue()));
    }

    @Test
    void processFindForm_returnNone() throws Exception {
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owner/findOwners"))
                .andExpect(model().attributeExists("owner"));

    }

    @Test
    void processFindForm_returnOne() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString())).
                thenReturn(List.of(Owner.builder().id(1L).build()));


        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    void processFindForm_returnMany() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString())).
                thenReturn(List.of(
                        Owner.builder().id(1L).build(),
                        Owner.builder().id(2L).build()
                ));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owner/ownersList"))
                .andExpect(model().attribute("selections", hasSize(2)));

    }
}