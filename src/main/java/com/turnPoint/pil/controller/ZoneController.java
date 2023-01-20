package com.turnPoint.pil.controller;

import com.turnPoint.pil.model.Zone;
import com.turnPoint.pil.model.ZoneWrapper;
import com.turnPoint.pil.services.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    @PostMapping("/zone")
    public ZoneWrapper addZone(@RequestBody Zone zone) {
        var savedZone = zoneService.saveZone(zone);
        return ZoneWrapper.builder().zone(savedZone).message("Zona guardada").build();
    }

    @GetMapping("/zone")
    public List<Zone> listZone() {
        return zoneService.listZone();
    }

    @GetMapping("/zone/{id}")
    public ZoneWrapper listZoneById(@PathVariable Long id){
        var listZone = zoneService.listZoneById(id);
        if (listZone.isEmpty()){
            return ZoneWrapper.builder().zone(null).message("No se encontró la zona").build();
        }
        else{
            return ZoneWrapper.builder().zone(listZone.get()).message("Zona encontrada").build();
        }
    }

}
