package com.telemedicina.telemedicina_back.controller;

import com.telemedicina.telemedicina_back.model.Paciente;
import com.telemedicina.telemedicina_back.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class PacienteController {

    @Autowired
    public PacienteRepository pacienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Listar
    @GetMapping(value = "/paciente")
    public Iterable<Paciente> getAllPacientes(){
        return pacienteRepository.findAll();
    }

    //Registrar
    @PostMapping(value = "/paciente/new")
    public Paciente savePaciente( @RequestBody Paciente paciente){
        paciente.setContraseña(passwordEncoder.encode(paciente.getContraseña()));
        return pacienteRepository.save(paciente);
    }

    //Eliminar
    @DeleteMapping(value= "/paciente/delete/{id}")
    public void deletePaciente(@PathVariable Integer id){
        pacienteRepository.deleteById(id);
    }


    //Login
    /*@PostMapping(value = "/paciente/login")
    public ResponseEntity<?> login(@RequestBody Paciente paciente) {
        Paciente pacienteExistente = pacienteRepository.findByCorreo(paciente.getCorreo());

        String correo = paciente.getCorreo();
        String rawPassword = paciente.getContraseña();

        if (correo == null || correo.isEmpty() || rawPassword == null || rawPassword.isEmpty()) {
            return ResponseEntity.badRequest().body("Correo y contraseña son obligatorios");
        }



        if (pacienteExistente == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }

        boolean passwordCorrecta = passwordEncoder.matches(paciente.getContraseña(), pacienteExistente.getContraseña());
        if (!passwordCorrecta) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }

        return ResponseEntity.ok("Inicio de sesión exitoso");
    }*/
    @PostMapping(value = "/paciente/login")
    public ResponseEntity<?> login(@RequestBody Paciente paciente) {
        String correo = paciente.getCorreo();
        String rawPassword = paciente.getContraseña();

        if (correo == null || correo.isEmpty() || rawPassword == null || rawPassword.isEmpty()) {
            return ResponseEntity.badRequest().body("Correo y contraseña son obligatorios");
        }

        Paciente pacienteExistente = pacienteRepository.findByCorreo(correo);

        if (pacienteExistente == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }

        boolean passwordCorrecta = passwordEncoder.matches(rawPassword, pacienteExistente.getContraseña());
        if (!passwordCorrecta) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }

        // Enviar objeto JSON con datos del paciente (sin contraseña)
        Map<String, String> response = new HashMap<>();
        response.put("nombre", pacienteExistente.getNombre());
        response.put("apellido", pacienteExistente.getApellido());
        response.put("correo", pacienteExistente.getCorreo());
        response.put("dni", pacienteExistente.getDni());
        return ResponseEntity.ok(response);
    }


}
