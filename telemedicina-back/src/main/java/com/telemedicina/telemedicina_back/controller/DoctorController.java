package com.telemedicina.telemedicina_back.controller;

import com.telemedicina.telemedicina_back.model.Doctor;
import com.telemedicina.telemedicina_back.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/doctores")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping
    public List<Doctor> obtenerDoctores(){
        return doctorRepository.findAll();
    }
}
