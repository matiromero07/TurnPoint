package com.turnPoint.pil.controller;

import com.turnPoint.pil.model.Supervisor;
import com.turnPoint.pil.repository.SupervisorRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SupervisorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SupervisorRepository supervisorRepository;

    Supervisor supervisor1;
    Supervisor supervisor2;

    @BeforeEach
    public void setup() {
        supervisor1 = new Supervisor("Matias", "Romero", 3517345982L);
        supervisor2 = new Supervisor("Paz", "Coll", 3517125422L);
    }

    @Test
    public void addSupervisor_success() throws Exception {

        Mockito.when(supervisorRepository.save(supervisor1)).thenReturn(supervisor1);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/supervisor")
                        .content("{\n" +
                                "    \"name\": \"Matias\",\n" +
                                "    \"surname\": \"Romero\",\n" +
                                "    \"phoneNumber\": \"3517872902\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Supervisor successfully registered")));

    }

    @Test
    public void listSupervisor_success() throws Exception {

        List<Supervisor> supervisors = new ArrayList<>(Arrays.asList(supervisor1, supervisor2));

        Mockito.when(supervisorRepository.findAll()).thenReturn(supervisors);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/supervisor")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Matias")));

    }

    @Test
    public void listSupervisorById_error() throws Exception {

        Mockito.when(supervisorRepository.findById(supervisor1.getId())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/supervisor/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("No supervisor found")));

    }

    @Test
    public void listSupervisorById_success() throws Exception {
        Mockito.when(supervisorRepository.findById(1L)).thenReturn(Optional.ofNullable(supervisor1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/supervisor/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Supervisor found")));

    }

    @Test
    public void editSupervisor_error() throws Exception {

        Mockito.when(supervisorRepository.save(supervisor1)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/supervisor/1")
                        .content("{\n" +
                                "    \"name\": \"Matias\",\n" +
                                "    \"surname\": \"Romero\",\n" +
                                "    \"phoneNumber\": \"3517872902\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("No se encontró el supervisor")));
    }

    @Test
    public void editSupervisor_success() throws Exception {

        Mockito.when(supervisorRepository.findById(1L)).thenReturn(Optional.ofNullable(supervisor1));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/supervisor/1")
                        .content("{\n" +
                                "    \"name\": \"Matias\",\n" +
                                "    \"surname\": \"Romero\",\n" +
                                "    \"phoneNumber\": \"3517872902\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Supervisor editado")));
    }

    @Test
    public void deleteSupervisor_error() throws Exception {

        Mockito.when(supervisorRepository.findById(supervisor1.getId())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/supervisor/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("No se encontró el supervisor")));
    }

    @Test
    public void deleteSupervisor_success() throws Exception {

        Mockito.when(supervisorRepository.findById(1L)).thenReturn(Optional.ofNullable(supervisor1));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/supervisor/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Supervisor eliminado")));
    }

}
