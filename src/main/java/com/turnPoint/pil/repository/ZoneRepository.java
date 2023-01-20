package com.turnPoint.pil.repository;

import com.turnPoint.pil.model.Zone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends CrudRepository<Zone, Long> {

}
