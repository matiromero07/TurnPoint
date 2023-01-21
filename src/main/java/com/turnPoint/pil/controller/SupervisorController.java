package com.turnPoint.pil.controller;

import com.turnPoint.pil.model.Supervisor;
import com.turnPoint.pil.model.SupervisorWrapper;
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
}
