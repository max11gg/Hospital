package com.example.springmodels.controllers;

import com.example.springmodels.models.Policlinic;
import com.example.springmodels.repository.PoliclinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@PreAuthorize("hasAnyAuthority('USER','MANAGER','ADMIN')")
@Controller
public class PoliclinicController {
    @Autowired
    private PoliclinicRepository policlinicRepository;

    @GetMapping("/policlinic")
    public String policlinicMain(Model model) {
        Iterable<Policlinic> policlinics = policlinicRepository.findAll();
        model.addAttribute("policlinics", policlinics);
        return "policlinic";
    }

    @GetMapping("/policlinic/add")
    public String policlinicAdd(@ModelAttribute("policlinics") Policlinic policlinic) {
        return "policlinic-add";
    }

    @PostMapping("/policlinic/add")
    public String policlinicPostAdd(@ModelAttribute("policlinics") @Valid Policlinic policlinic, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "policlinic-add";
        }
        policlinicRepository.save(policlinic);
        return "redirect:/policlinic";
    }

    @GetMapping("/policlinic/{id}/edit")
    public  String policlinicDetails(@PathVariable(value = "id") long id, Model model)
    {
        Policlinic policlinic = policlinicRepository.findById(id).orElseThrow();
        model.addAttribute("policlinic", policlinic);
        return "policlinic-edit";
    }

    @PostMapping ("/policlinic/{id}/edit")
    public  String policlinicUpdate(@ModelAttribute("bed") @Valid Policlinic policlinic,
                             BindingResult bindingResult,
                             @PathVariable(value = "id") long id)
    {
        if(bindingResult.hasErrors())
            return "policlinic-edit";
        policlinicRepository.save(policlinic);
        return "redirect:/policlinic";
    }

    @GetMapping("/policlinic/{id}/remove")
    public  String policlinicDelete(@PathVariable(value = "id") long id, Model model)
    {
        Policlinic policlinic = policlinicRepository.findById(id).orElseThrow();
        policlinicRepository.delete(policlinic);
        return "redirect:/policlinic";
    }
}
