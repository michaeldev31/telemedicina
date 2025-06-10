package com.telemedicina.telemedicina_back.repository;

import com.telemedicina.telemedicina_back.model.Cita;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends CrudRepository<Cita, Integer> {
List<Cita> findByDni(String dni);
}
