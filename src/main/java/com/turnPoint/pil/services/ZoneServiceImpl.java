package com.turnPoint.pil.services;

import com.turnPoint.pil.model.Zone;
import com.turnPoint.pil.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    ZoneRepository zoneRepository;

    @Override
    public Zone saveZone(Zone zone) {
        return zoneRepository.save(zone);
    }

    @Override
    public List<Zone> listZone() {
        return (List<Zone>) zoneRepository.findAll();
    }

}
