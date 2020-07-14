package ru.kuznetsov.stories.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kuznetsov.stories.models.User;
import ru.kuznetsov.stories.services.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/reg")
    public String regGet(Model model) {
        model.addAttribute("user",new User());
        return "reg";
    }

    @PostMapping("/reg")
    public String regPost(@Valid User user, BindingResult result, Model model) {
        userService.validate(user, result);
        if(!result.hasErrors()){
            model.addAttribute("noErrors",true);
            userService.save(user);
        }
        return "reg";
    }

    @GetMapping("/login")
    public String login(@RequestParam(name = "error", required = false) Boolean error,
                        Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", true);
        }
        return "login";
    }
}
