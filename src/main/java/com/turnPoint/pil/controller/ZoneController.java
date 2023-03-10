package com.turnPoint.pil.controller;

import com.turnPoint.pil.model.Zone;
import com.turnPoint.pil.model.ZoneWrapper;
import com.turnPoint.pil.services.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ZoneWrapper listZoneById(@PathVariable Long id) {
        var listZone = zoneService.listZoneById(id);
        if (listZone.isEmpty()) {
            return ZoneWrapper.builder().zone(null).message("No se encontrĂ³ la zona").build();
        } else {
            return ZoneWrapper.builder().zone(listZone.get()).message("Zona encontrada").build();
        }
    }

    @PutMapping("/zone/{id}")
    public ZoneWrapper editZone(@RequestBody Zone zone, @PathVariable Long id) {
        var listZone = zoneService.listZoneById(id);

        if (listZone.isEmpty()) {
            return ZoneWrapper.builder().zone(null).message("No se encontrĂ³ la zona").build();
        } else {

            zone.setId(id);
            zone.setAddress(zone.getAddress());
            zone.setLat(zone.getLat());
            zone.setLon(zone.getLon());

            var savedZone = zoneService.editZone(zone);
            return ZoneWrapper.builder().zone(savedZone).message("Zona editada").build();
        }
    }

    @DeleteMapping("/zone/{id}")
    public ZoneWrapper deleteZone(@PathVariable Long id) {
        var listZone = zoneService.listZoneById(id);

        if (listZone.isEmpty()) {
            return ZoneWrapper.builder().zone(null).message("No se encontrĂ³ la zona").build();
        } else {

            zoneService.deleteZone(id);
            return ZoneWrapper.builder().zone(listZone.get()).message("Zona borrada").build();
        }

    }

    @GetMapping("/calculateDistance/{id}/{id2}")
    public String calculateDistance(@PathVariable Long id, @PathVariable Long id2) {
        var listZone = zoneService.listZoneById(id);
        var listZone2 = zoneService.listZoneById(id2);

        if (listZone.isEmpty() || listZone2.isEmpty()) {
            return "No se encontrĂ³ la zona";
        } else {
            return zoneService.calculateDistance(listZone, listZone2);
        }

    }

}
