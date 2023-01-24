package com.turnPoint.pil.services;

import com.turnPoint.pil.model.Supervisor;
import com.turnPoint.pil.model.Zone;
import com.turnPoint.pil.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupervisorServiceImpl implements SupervisorService {

    @Autowired
    SupervisorRepository supervisorRepository;

    @Override
    public Supervisor saveSupervisor(Supervisor supervisor) {

        return supervisorRepository.save(supervisor);
    }

    @Override
    public List<Supervisor> listSupervisor() {
        return (List<Supervisor>) supervisorRepository.findAll();
    }

    @Override
    public Optional<Supervisor> listSupervisorById(Long id) {

        return supervisorRepository.findById(id);
    }

    public Supervisor editSupervisor(Supervisor supervisor){
        return supervisorRepository.save(supervisor);
    }
    
    public void deleteSupervisor(Long id){
        supervisorRepository.deleteById(id);
    }
}

