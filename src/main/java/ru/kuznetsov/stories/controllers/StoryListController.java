package ru.kuznetsov.stories.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kuznetsov.stories.models.Comment;
import ru.kuznetsov.stories.models.Story;
import ru.kuznetsov.stories.models.User;
import ru.kuznetsov.stories.services.*;

import java.security.Principal;


@Controller
public class StoryListController {

    private StoryService storyService;
    private GenreService genreService;
    private UserService userService;
    private UserStoryMarkService userStoryMarkService;
    private CommentService commentService;

    @Autowired
    public StoryListController(StoryService storyService, GenreService genreService, UserService userService, UserStoryMarkService userStoryMarkService, CommentService commentService) {
        this.storyService = storyService;
        this.genreService = genreService;
        this.userService = userService;
        this.userStoryMarkService = userStoryMarkService;
        this.commentService = commentService;
    }

    @GetMapping("/stories")
    public String stories(Model model,
                          @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("page", storyService.getAllStories(pageable));
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("url", "/stories");
        return "stories";
    }

    @GetMapping("/stories/{id}")
    public String getStory(@PathVariable String id, Model model, Principal principal,
                           @PageableDefault(sort={"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        System.out.println("Обработчик истории");
        Story story = storyService.getById(Long.parseLong(id));
        if (story == null) {
            return "redirect:/stories";
        }
        User user = null;
        if (principal != null) {
            user = userService.findByLogin(principal.getName());
        }
        if (user != null) {
            String mark = userStoryMarkService.getMark(user, story);
            if (mark != null) {
                model.addAttribute("mark", mark);
            }
        }
        Page<Comment> comments = commentService.getAllComments(story, pageable);
        model.addAttribute("story", story);
        model.addAttribute("comments", comments);
        model.addAttribute("url", "/stories/" + id);
        return "story";
    }

    @GetMapping("/stories/filter")
    public String getFilteredStories(@RequestParam(name = "author", required = false) String author,
                                     @RequestParam(name = "title", required = false) String title,
                                     @RequestParam(name = "genre") String genre
                                     ) {
        String storyId = "";
        if(!title.equals("")){
            try{
                storyId = storyService.getByTitle(title).getId().toString();
            } catch (Exception ex){
                storyId = "NotFound";
            }
        }
        return "redirect:/stories/author" + author + "/story" + storyId + "/genre" + genre+"/";
    }

    @GetMapping("/stories/author{authorLogin}/story{storyId}/genre{genreId}/")
    public String getFilteredStories(@PathVariable(name = "authorLogin", required = false) String authorLogin,
                                     @PathVariable(name = "storyId", required = false) String storyId,
                                     @PathVariable(name = "genreId") String genreId,
                                     @PageableDefault(sort={"id"}, direction = Sort.Direction.DESC, size = 10)
                                                 Pageable pageable,
                                     Model model
    ) {
        String url = "/stories/author" + authorLogin + "/story" + storyId + "/genre" + genreId+"/";
        if(authorLogin.equals("")) authorLogin = "%";
        if(storyId.equals("")) storyId = "%";
        if(genreId.equals("")) genreId = "%";
        Page<Story> filteredStories = storyService.findFiltered(authorLogin,storyId,genreId,pageable);
        model.addAttribute("page", filteredStories);
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("url", url);
        return "stories";
    }

}
