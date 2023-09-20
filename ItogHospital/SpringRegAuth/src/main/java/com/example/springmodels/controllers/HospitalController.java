package com.example.springmodels.controllers;

import com.example.springmodels.models.Hospital;
import com.example.springmodels.models.Laboratory;
import com.example.springmodels.models.Palat;
import com.example.springmodels.repository.HospitalRepository;
import com.example.springmodels.repository.LaboratoryRepository;
import com.example.springmodels.repository.PalatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HospitalController {
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private PalatRepository palatRepository;
    @Autowired
    private LaboratoryRepository laboratoryRepository;

    @GetMapping("/hospital")
    public String hospitalMain(Model model) {
        Iterable<Hospital> hospitals = hospitalRepository.findAll();
        model.addAttribute("hospitals", hospitals);
        return "hospital";
    }

    @GetMapping("/hospital/add")
    public String hospitalAdd(@ModelAttribute("hospitals") Hospital hospital, Model type) {
        Iterable<Palat> palats = palatRepository.findAll();
        Iterable<Laboratory> laboratories = laboratoryRepository.findAll();
        type.addAttribute("palats", palats);
        type.addAttribute("laboratories", laboratories);
        return "hospital-add";
    }

    @PostMapping("/hospital/add")
    public String hospitalPostAdd(@ModelAttribute("hospitals") @Valid Hospital hospital,
                                 BindingResult bindingResult,
                                 @RequestParam String numberPalat,
                                 @RequestParam String nameLaboratory,
                                 Model type) {
        if (bindingResult.hasErrors()) {
            Iterable<Palat> palat = palatRepository.findAll();
            Iterable<Laboratory> laboratory = laboratoryRepository.findAll();
            type.addAttribute("palat", palat);
            type.addAttribute("laboratory", laboratory);
            return "hospital-add";
        }
        hospital.setPalat(palatRepository.findByNumberPalat(numberPalat));
        hospital.setLaboratory(laboratoryRepository.findByNameLaboratory(nameLaboratory));
        hospitalRepository.save(hospital);
        return "redirect:/hospital";
    }

    @GetMapping("/hospital/{id}/edit")
    public String hospitalDetails(@PathVariable(value = "id") long id, Model model, Model type)
    {
        Iterable<Palat> palats = palatRepository.findAll();
        Iterable<Laboratory> laboratories = laboratoryRepository.findAll();
        type.addAttribute("palats", palats);
        type.addAttribute("laboratories", laboratories);
        Hospital hospital = hospitalRepository.findById(id).orElseThrow();
        model.addAttribute("hospital", hospital);
        return "hospital-edit";
    }

    @PostMapping ("/hospital/{id}/edit")
    public  String hospitalUpdate(@ModelAttribute("hospital") @Valid Hospital hospital,
                                 BindingResult bindingResult,
                                 @PathVariable(value = "id") long id)
    {
        if(bindingResult.hasErrors())
            return "hospital-edit";
        hospitalRepository.save(hospital);
        return "redirect:/hospital";
    }

    @GetMapping("/hospital/{id}/remove")
    public  String hospitalDelete(@PathVariable(value = "id") long id, Model model)
    {
        Hospital hospital = hospitalRepository.findById(id).orElseThrow();
        hospitalRepository.delete(hospital);
        return "redirect:/hospital";
    }
}
