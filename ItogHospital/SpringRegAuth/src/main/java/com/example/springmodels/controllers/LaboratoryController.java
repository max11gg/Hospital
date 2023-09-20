package com.example.springmodels.controllers;

import com.example.springmodels.models.Bed;
import com.example.springmodels.models.Laboratory;
import com.example.springmodels.repository.BedRepository;
import com.example.springmodels.repository.LaboratoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LaboratoryController {
    @Autowired
    private LaboratoryRepository laboratoryRepository;

    @GetMapping("/laboratory")
    public String laboratoryMain(Model model) {
        Iterable<Laboratory> laboratorys = laboratoryRepository.findAll();
        model.addAttribute("laboratorys", laboratorys);
        return "laboratory";
    }

    @GetMapping("/laboratory/add")
    public String laboratoryAdd(@ModelAttribute("laboratorys") Laboratory laboratory) {
        return "laboratory-add";
    }

    @PostMapping("/laboratory/add")
    public String laboratoryPostAdd(@ModelAttribute("laboratorys") @Valid Laboratory laboratory, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "laboratory-add";
        }
        laboratoryRepository.save(laboratory);
        return "redirect:/laboratory";
    }

    @GetMapping("/laboratory/{id}/edit")
    public  String laboratoryDetails(@PathVariable(value = "id") long id, Model model)
    {
        Laboratory laboratory = laboratoryRepository.findById(id).orElseThrow();
        model.addAttribute("laboratory", laboratory);
        return "laboratory-edit";
    }

    @PostMapping ("/laboratory/{id}/edit")
    public  String laboratoryUpdate(@ModelAttribute("laboratory") @Valid Laboratory laboratory,
                             BindingResult bindingResult,
                             @PathVariable(value = "id") long id)
    {
        if(bindingResult.hasErrors())
            return "laboratory-edit";
        laboratoryRepository.save(laboratory);
        return "redirect:/laboratory";
    }

    @GetMapping("/laboratory/{id}/remove")
    public  String laboratoryDelete(@PathVariable(value = "id") long id, Model model)
    {
        Laboratory laboratory = laboratoryRepository.findById(id).orElseThrow();
        laboratoryRepository.delete(laboratory);
        return "redirect:/laboratory";
    }
}
