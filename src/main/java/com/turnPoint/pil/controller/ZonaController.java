package com.turnPoint.pil.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ZonaController {


    @PostMapping("/cargar")
    public void cargarZona() {

    }

    @GetMapping("/cargar")
    public String consultarZona() {
        return "Hola";
    }

}
