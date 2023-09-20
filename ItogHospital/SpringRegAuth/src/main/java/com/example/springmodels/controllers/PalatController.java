package com.example.springmodels.controllers;

import com.example.springmodels.models.Bed;
import com.example.springmodels.models.Palat;
import com.example.springmodels.repository.BedRepository;
import com.example.springmodels.repository.PalatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PalatController {
    @Autowired
    private PalatRepository palatRepository;
    @Autowired
    private BedRepository bedRepository;

    @GetMapping("/palat")
    public String palatMain(Model model) {
        Iterable<Palat> palats = palatRepository.findAll();
        model.addAttribute("palats", palats);
        return "palat";
    }

    @GetMapping("/palat/add")
    public String palatAdd(@ModelAttribute("palats") Palat palat, Model position) {
        Iterable<Bed> bed = bedRepository.findAll();
        position.addAttribute("bed",bed);
        return "palat-add";
    }

    @PostMapping("/palat/add")
    public String palatPostAdd(@ModelAttribute("palats") @Valid Palat palat,
                                  BindingResult bindingResult, @RequestParam String numberBed, Model position) {
        if(bindingResult.hasErrors()) {
            Iterable<Bed> bed = bedRepository.findAll();
            position.addAttribute("bed", bed);
            return "bed-add";
        }
        palat.setBed(bedRepository.findByNumberBed(numberBed));
        palatRepository.save(palat);
        return "redirect:/palat";
    }
//доделать
    @GetMapping("/palat/{id}/edit")
    public  String palatDetails(@PathVariable(value = "id") long id, Model model, Model position)
    {
        Iterable<Bed> bed = bedRepository.findAll();
        position.addAttribute("bed",bed);
        Palat palat = palatRepository.findById(id).orElseThrow();
        model.addAttribute("palat",palat);
        return "palat-edit";
    }

    @PostMapping ("/palat/{id}/edit")
    public  String palatUpdate(@ModelAttribute("palat") @Valid Palat palat,
                                  BindingResult bindingResult,
                                  @PathVariable(value = "id") long id)
    {
        if(bindingResult.hasErrors())
            return "palat-edit";
        palatRepository.save(palat);
        return "redirect:/palat";
    }

    @GetMapping("/palat/{id}/remove")
    public  String palatDelete(@PathVariable(value = "id") long id, Model model)
    {
        Palat palat = palatRepository.findById(id).orElseThrow();
        palatRepository.delete(palat);
        return "redirect:/palat";
    }
}
