package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.service.authority.AuthorityService;
import ru.job4j.accidents.service.user.UserService;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private final PasswordEncoder encoder;
    private UserService userService;
    private AuthorityService authorityService;

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorityService.findByAuthority("ROLE_USER").get());
        Optional<User> userOptional = userService.save(user);
        if (userOptional.isEmpty()) {
            model.addAttribute("message", "Ошибка при регистрации пользователя. Попробуйте использовать другое имя");
            return "reg";
        }
        return "redirect:/login";
    }
}