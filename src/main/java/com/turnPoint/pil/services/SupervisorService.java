package com.turnPoint.pil.services;

import com.turnPoint.pil.model.Supervisor;
import com.turnPoint.pil.model.Zone;


import java.util.List;
import java.util.Optional;

public interface SupervisorService {
    Supervisor saveSupervisor(Supervisor supervisor);

    List<Supervisor> listSupervisor();

    Optional<Supervisor> listSupervisorById(Long id);

    Supervisor editSupervisor(Supervisor supervisor);
}
