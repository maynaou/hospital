package com.example.hospital.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.hospital.repository.PatientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.example.hospital.entities.Patient;

@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
    @GetMapping("/index")
    public String index(Model model) {
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("listPatients",patients);
        return "patients";
    }
    @GetMapping("/deletePatient")
    public String delete(@RequestParam(name = "id") Long id) {
            patientRepository.deleteById(id);
            return "redirect:/index";
    }
}
