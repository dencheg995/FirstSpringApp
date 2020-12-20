package ru.kuznetsov.stories.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kuznetsov.stories.models.Story;
import ru.kuznetsov.stories.models.User;
import ru.kuznetsov.stories.services.GenreService;
import ru.kuznetsov.stories.services.StoryService;

import java.util.List;


@Controller
public class PublishStoryController {

    private GenreService genreService;
    private StoryService storyService;

    @Autowired
    public PublishStoryController(GenreService genreService, StoryService storyService) {
        this.genreService = genreService;
        this.storyService = storyService;
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
                              @AuthenticationPrincipal User currentUser
    ) {
        if (storyService.validatePublishForm(title, shortDesc, fullText, genres, model)) {
            model.addAttribute("genres", genreService.getAllGenres());
            return "publish";
        }
        Story story = new Story(title, shortDesc, fullText, currentUser);
        storyService.addGenresToStory(story, genres);
        storyService.save(story);
        return "redirect:/";
    }
}
