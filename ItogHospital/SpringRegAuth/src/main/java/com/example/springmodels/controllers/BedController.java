package com.example.springmodels.controllers;

import com.example.springmodels.models.Bed;
import com.example.springmodels.repository.BedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@PreAuthorize("hasAnyAuthority('USER','MANAGER','ADMIN')")
@Controller
public class BedController {

    @Autowired
    private BedRepository bedRepository;

    @GetMapping("/bed")
    public String bedMain(Model model) {
        Iterable<Bed> beds = bedRepository.findAll();
        model.addAttribute("beds", beds);
        return "bed";
    }

    @GetMapping("/bed/add")
    public String bedAdd(@ModelAttribute("beds") Bed bed) {
        return "bed-add";
    }

    @PostMapping("/bed/add")
    public String bedPostAdd(@ModelAttribute("beds") @Valid Bed bed, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "bed-add";
        }
        bedRepository.save(bed);
        return "redirect:/bed";
    }

    @GetMapping("/bed/{id}/edit")
    public  String bedDetails(@PathVariable(value = "id") long id, Model model)
    {
        Bed bed = bedRepository.findById(id).orElseThrow();
        model.addAttribute("bed", bed);
        return "bed-edit";
    }

    @PostMapping ("/bed/{id}/edit")
    public  String bedUpdate(@ModelAttribute("bed") @Valid Bed bed,
                                BindingResult bindingResult,
                                @PathVariable(value = "id") long id)
    {
        if(bindingResult.hasErrors())
            return "bed-edit";
        bedRepository.save(bed);
        return "redirect:/bed";
    }

    @GetMapping("/bed/{id}/remove")
    public  String bedDelete(@PathVariable(value = "id") long id, Model model)
    {
        Bed bed = bedRepository.findById(id).orElseThrow();
        bedRepository.delete(bed);
        return "redirect:/bed";
    }
}
