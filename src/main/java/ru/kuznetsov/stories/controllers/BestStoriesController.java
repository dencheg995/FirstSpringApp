package ru.kuznetsov.stories.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kuznetsov.stories.models.Story;
import ru.kuznetsov.stories.services.StoryService;

@Controller
public class BestStoriesController {
    private StoryService storyService;

    @Autowired
    public BestStoriesController(StoryService storyService){
        this.storyService = storyService;
    }

    @GetMapping("/best")
    public String getBestStories(Model model, Pageable pageable){
        Page<Story> bestStories = storyService.getBestStories(pageable);

        model.addAttribute("bestStories", bestStories);
        model.addAttribute("url", "/best");
        return "best";
    }
}
