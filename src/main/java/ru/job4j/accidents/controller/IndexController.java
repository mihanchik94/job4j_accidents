package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.service.AccidentService;

@Controller
@AllArgsConstructor
public class IndexController {
    private final AccidentService accidentService;

    @GetMapping("/accidents")
    public String getIndex(Model model) {
        model.addAttribute("user", "UserName");
        model.addAttribute("accidents", accidentService.findAll());
        return "index";
    }
}