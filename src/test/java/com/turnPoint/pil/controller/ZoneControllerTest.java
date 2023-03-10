package com.turnPoint.pil.controller;

import com.turnPoint.pil.model.Zone;
import com.turnPoint.pil.repository.ZoneRepository;
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
public class ZoneControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ZoneRepository zoneRepository;

    Zone zone1;
    Zone zone2;
    Zone zone3;

    @BeforeEach
    public void setup() {
        zone1 = new Zone("Colon 1930", "123.322.124.21", "123.422.231.11");
        zone2 = new Zone("27 de abril 124", "211.221.412.51", "212.122.412.21");
        zone3 = new Zone("Independencia 821", "221.122.512.11", "112.522.490.10");
    }


    @Test
    public void getAllZone_success() throws Exception {

        List<Zone> zones = new ArrayList<>(Arrays.asList(zone1, zone2, zone3));

        Mockito.when(zoneRepository.findAll()).thenReturn(zones);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/zone")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[2].address", Matchers.is("Independencia 821")));

    }

    @Test
    public void addZone_success() throws Exception {
        Mockito.when(zoneRepository.save(zone1)).thenReturn(zone1);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/zone")
                        .content("{\n" +
                                "    \"address\": \"Independencia 821\",\n" +
                                "    \"lat\": \"200.012.011.1\",\n" +
                                "    \"lon\": \"124.212.122.5\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Zona guardada")));

    }

    @Test
    public void listZoneById_error() throws Exception {

        Mockito.when(zoneRepository.findById(zone1.getId())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/zone/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("No se encontr?? la zona")));

    }

    @Test
    public void listZoneById_success() throws Exception {

        Mockito.when(zoneRepository.findById(1L)).thenReturn(Optional.ofNullable(zone1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/zone/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Zona encontrada")));

    }

    @Test
    public void editZone_error() throws Exception {
        Mockito.when(zoneRepository.save(zone1)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/zone/1")
                        .content("{\n" +
                                "    \"address\": \"Independencia 821\",\n" +
                                "    \"lat\": \"200.122.421.2\",\n" +
                                "    \"lon\": \"152.421.122.1\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("No se encontr?? la zona")));
    }

    @Test
    public void editZone_success() throws Exception {
        Mockito.when(zoneRepository.findById(1L)).thenReturn(Optional.ofNullable(zone1));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/zone/1")
                        .content("{\n" +
                                "    \"address\": \"Independencia 821\",\n" +
                                "    \"lat\": \"200.122.421.2\",\n" +
                                "    \"lon\": \"152.421.122.1\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Zona editada")));
    }

    @Test
    public void deleteZone_error() throws Exception {
        Mockito.when(zoneRepository.findById(zone1.getId())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/zone/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("No se encontr?? la zona")));
    }

    @Test
    public void deleteZone_success() throws Exception {
        Mockito.when(zoneRepository.findById(1L)).thenReturn(Optional.ofNullable(zone1));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/zone/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Zona borrada")));
    }

    @Test
    public void calculateDistance_error() throws Exception {
        Mockito.when(zoneRepository.findById(zone1.getId())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/calculateDistance/1/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("No se encontr?? la zona"));
    }

    @Test
    public void calculateDistance_success() throws Exception{
        Mockito.when(zoneRepository.findById(1L)).thenReturn(Optional.ofNullable(zone1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/calculateDistance/1/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
