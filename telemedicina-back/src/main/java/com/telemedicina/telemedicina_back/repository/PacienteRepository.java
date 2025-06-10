package com.telemedicina.telemedicina_back.repository;

import com.telemedicina.telemedicina_back.model.Paciente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente, Integer> {
    Paciente findByCorreo(String correo);

    Optional<Paciente> findByDni(String dni);



}

