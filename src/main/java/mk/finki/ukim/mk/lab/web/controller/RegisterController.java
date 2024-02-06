package mk.finki.ukim.mk.lab.web.controller;

import lombok.extern.slf4j.Slf4j;
import mk.finki.ukim.mk.lab.exceptions.InvalidArgumentException;
import mk.finki.ukim.mk.lab.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.mk.lab.model.Role;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
@Slf4j
public class RegisterController {



    private final UserService userService;

    public RegisterController( UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodycontent", "register");
        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam Role role) {
        try {
            log.debug("Trying to register user: " + username + " and with role: " + role.name());
            this.userService.register(username, password, repeatedPassword, name, surname, role);
            return "redirect:/login";
        } catch (InvalidArgumentException | PasswordsDoNotMatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }


}
