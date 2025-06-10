package com.telemedicina.telemedicina_back.controller;

import com.telemedicina.telemedicina_back.model.Cita;
import com.telemedicina.telemedicina_back.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/citas")

public class CitaController {

    @Autowired
    public CitaRepository citaRepository;

    @GetMapping("/citas")
    public Iterable<Cita> getAllCitas(){
        return citaRepository.findAll();
    }

    @PostMapping("/citas/nuevo")
    public Cita saveCita(@RequestBody Cita cita){
        return citaRepository.save(cita);
    }

    @DeleteMapping("/citas/eliminar/{id}")
    public void deleteCita(@PathVariable Integer id){
        citaRepository.deleteById(id);
    }

    @GetMapping("/dni/{dni}")
    public List<Cita> obtenerCitasPorDni(@PathVariable String dni){
        return citaRepository.findByDni(dni);
    }


}
