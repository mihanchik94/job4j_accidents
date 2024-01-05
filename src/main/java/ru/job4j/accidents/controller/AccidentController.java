package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.service.accident.AccidentService;
import ru.job4j.accidents.service.accidentType.AccidentTypeService;
import ru.job4j.accidents.service.rule.RuleService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.Set;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    @GetMapping("/accidents")
    public String viewAllAccidents(Model model) {
        model.addAttribute("accidents", accidentService.findAll());
        model.addAttribute("rules", ruleService.findAll());
        return "accident/allAccidents";
    }

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", accidentTypeService.findAll());
        model.addAttribute("rules", ruleService.findAll());
        return "accident/createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        Set<Rule> rules = ruleService.convertFromStringArrayToRulesSet(ids);
        accident.setType(accidentTypeService.findById(accident.getType().getId()).get());
        accident.setRules(rules);
        accidentService.create(accident);
        return "redirect:/accidents";
    }

    @GetMapping("/formUpdateAccident")
    public String formUpdate(@RequestParam("id") int id, Model model) {
        Optional<Accident> accidentOptional = accidentService.findById(id);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "Пользователь с указанным id не найден");
            return "errors/404";
        }
        model.addAttribute("accident", accidentOptional.get());
        model.addAttribute("types", accidentTypeService.findAll());
        model.addAttribute("rules", ruleService.findAll());
        return "accident/updateAccident";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident, @RequestParam("rIds") String[] ids, Model model) {
        Set<Rule> rules = ruleService.convertFromStringArrayToRulesSet(ids);
        accident.setRules(rules);
        Optional<Accident> optionalAccident = accidentService.update(accident);
        if (optionalAccident.isEmpty()) {
            model.addAttribute("message", "Ошибка во время обновления");
            return "errors/404";
        }
        return "redirect:/";
    }
}