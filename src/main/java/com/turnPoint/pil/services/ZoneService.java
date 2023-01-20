package com.turnPoint.pil.services;

import com.turnPoint.pil.model.Zone;

import java.util.List;
import java.util.Optional;

public interface ZoneService {

    Zone saveZone(Zone zone);

    List<Zone> listZone();

    Optional <Zone> listZoneById(Long id);

}
