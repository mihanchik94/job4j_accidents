package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.accident.AccidentService;
import ru.job4j.accidents.service.accidentType.AccidentTypeService;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;

    @GetMapping("/accidents")
    public String viewAllAccidents(Model model) {
        model.addAttribute("accidents", accidentService.findAll());
        return "allAccidents";
    }

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", accidentTypeService.findAll());
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        accident.setType(accidentTypeService.findById(accident.getType().getId()).get());
        accidentService.create(accident);
        return "redirect:/accidents";
    }

    @GetMapping("/formUpdateAccident")
    public String formUpdate(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.findById(id).get());
        model.addAttribute("types", accidentTypeService.findAll());
        return "updateAccident";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident) {
        accidentService.create(accident);
        return "redirect:/";
    }
}