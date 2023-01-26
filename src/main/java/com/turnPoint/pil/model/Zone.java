package com.turnPoint.pil.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String lat;
    private String lon;

    public Zone(String address, String lat, String lon){
        this.address = address;
        this.lat = lat;
        this.lon = lon;
    }

    public String coordinates(){
        return "Latitud: " + lat + " - Longitud: " + lon;
    }

    public Long calculateDistance(Zone zoneOther){

        return Long.parseLong(lat) - Long.parseLong(zoneOther.lat);
    }

}
