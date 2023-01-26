package com.turnPoint.pil.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZoneTest {


    @Test
    void coordinates() {
        Zone zone = new Zone("asda", "21321", "21312");

        Assertions.assertEquals("Latitud: 21321 - Longitud: 21312", zone.coordinates());

    }

    @Test
    void calculateDistance(){

        Zone zone = new Zone("asda", "100", "200");
        Zone otherZone = new Zone("asda", "10", "100");

        Assertions.assertEquals(90L, zone.calculateDistance(otherZone));

    }

}