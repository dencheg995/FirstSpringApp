package ru.kuznetsov.stories.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kuznetsov.stories.models.Story;
import ru.kuznetsov.stories.models.User;
import ru.kuznetsov.stories.services.GenreService;
import ru.kuznetsov.stories.services.StoryService;
import ru.kuznetsov.stories.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String test() {
        return "main";
    }

}
