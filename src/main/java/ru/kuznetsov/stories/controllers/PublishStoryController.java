package ru.kuznetsov.stories.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kuznetsov.stories.models.Story;
import ru.kuznetsov.stories.models.User;
import ru.kuznetsov.stories.services.GenreService;
import ru.kuznetsov.stories.services.StoryService;
import ru.kuznetsov.stories.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.regex.Pattern;

@Controller
public class PublishStoryController {

    private UserService userService;
    private GenreService genreService;
    private StoryService storyService;

    @Autowired
    public PublishStoryController(UserService userService, GenreService genreService, StoryService storyService) {
        this.genreService = genreService;
        this.storyService = storyService;
        this.userService = userService;
    }

    @GetMapping("/publish")
    public String publishGet(Model model) {
        model.addAttribute("genres", genreService.getAllGenres());
        return "publish";
    }

    @PostMapping("/publish")
    public String publishPost(@RequestParam(name = "title", required = false) String title,
                              @RequestParam(name = "fullText", required = false) String fullText,
                              @RequestParam(name = "genres", required = false) List<String> genres,
                              @RequestParam(name = "shortDesc", required = false) String shortDesc,
                              Model model,
                              Principal principal
    ) {
        User currentUser = userService.findByLogin(principal.getName());
        if(storyService.validatePublishForm(title, shortDesc, fullText, genres, model)){
            model.addAttribute("genres", genreService.getAllGenres());
            return "publish";
        }
        Story story = new Story(title, shortDesc, fullText, currentUser);
        storyService.addGenresToStory(story, genres);
        storyService.save(story);
        return "redirect:/";
    }
}
