package com.turnPoint.pil.services;

import com.turnPoint.pil.model.Zone;

import java.util.List;

public interface ZoneService {

    Zone saveZone(Zone zone);

    List<Zone> listZone();

}
