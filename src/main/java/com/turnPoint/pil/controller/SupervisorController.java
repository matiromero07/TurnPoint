package com.turnPoint.pil.controller;

import com.turnPoint.pil.model.Supervisor;
import com.turnPoint.pil.model.SupervisorWrapper;
import com.turnPoint.pil.model.Zone;
import com.turnPoint.pil.model.ZoneWrapper;
import com.turnPoint.pil.services.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupervisorController {
    @Autowired
    private SupervisorService supervisorService;

    @PostMapping("/supervisor")
    public SupervisorWrapper addSupervisor(@RequestBody Supervisor supervisor) {
        var savedSupervisor = supervisorService.saveSupervisor(supervisor);
        return SupervisorWrapper.builder().supervisor(savedSupervisor).message("Supervisor successfully registered").build();
    }

    @GetMapping("/supervisor")
    public List<Supervisor> listSupervisor()
    {
        return supervisorService.listSupervisor();
    }

    @GetMapping("/supervisor/{id}")
    public SupervisorWrapper listSupervisorById(@PathVariable Long id){
        var listSupervisor = supervisorService.listSupervisorById(id);
        if (listSupervisor.isEmpty()){
            return SupervisorWrapper.builder().supervisor(null).message("No supervisor found").build();
        }
        else{
            return SupervisorWrapper.builder().supervisor(listSupervisor.get()).message("Supervisor found").build();
        }
    }

    @PutMapping("/supervisor/{id}")
    public SupervisorWrapper editSupervisor(@RequestBody Supervisor supervisor, @PathVariable Long id) {
        var listSupervisor = supervisorService.listSupervisorById(id);

        if (listSupervisor.isEmpty()) {
            return SupervisorWrapper.builder().supervisor(null).message("No se encontró el supervisor").build();
        } else {

            supervisor.setId(id);
            supervisor.setName(supervisor.getName());
            supervisor.setSurname(supervisor.getSurname());
            supervisor.setPhoneNumber(supervisor.getPhoneNumber());

            var savedSupervisor = supervisorService.saveSupervisor(supervisor);
            return SupervisorWrapper.builder().supervisor(savedSupervisor).message("Supervisor editado").build();
        }
    }
}
