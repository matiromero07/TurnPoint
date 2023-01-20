package com.turnPoint.pil.controller;

import com.turnPoint.pil.model.Zone;
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
    public Zone addZone(@RequestBody Zone zone) {
        return zoneService.saveZone(zone);
    }

    @GetMapping("/zone")
    public List<Zone> listZone() {
        return zoneService.listZone();
    }

    @GetMapping("/zone/{id}")
    public Optional<Zone> listZoneById(@PathVariable Long id){
        return zoneService.listZoneById(id);
    }

}
