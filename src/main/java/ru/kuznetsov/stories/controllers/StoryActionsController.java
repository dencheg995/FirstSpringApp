package ru.kuznetsov.stories.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kuznetsov.stories.models.Comment;
import ru.kuznetsov.stories.models.Story;
import ru.kuznetsov.stories.models.User;
import ru.kuznetsov.stories.services.CommentService;
import ru.kuznetsov.stories.services.StoryService;
import ru.kuznetsov.stories.services.UserService;
import ru.kuznetsov.stories.services.UserStoryMarkService;

import java.security.Principal;

@Controller
public class StoryActionsController {

    private StoryService storyService;
    private CommentService commentService;
    private UserService userService;
    private UserStoryMarkService userStoryMarkService;

    @Autowired
    public StoryActionsController(StoryService storyService, CommentService commentService, UserService userService, UserStoryMarkService userStoryMarkService) {
        this.storyService = storyService;
        this.commentService = commentService;
        this.userService = userService;
        this.userStoryMarkService = userStoryMarkService;
    }

    @PostMapping("/stories/{id}/comment")
    public String commentAdd(@RequestParam(name = "text", required = false) String text,
                             @PathVariable String id, Model model, Principal principal) {
        Story story = storyService.getById(Long.parseLong(id));
        if (story == null) {
            return "redirect:/stories";
        }
        User user = userService.findByLogin(principal.getName());
        if (user == null) {
            return "redirect:/login";
        }
        if (!text.equals("")){
            commentService.save(new Comment(user,story,text));
        }

        return "redirect:/stories/"+id;
    }

    @PostMapping("/stories/{id}/setMark")
    public String setMark(@RequestParam(name="mark") String mark,
                          @PathVariable String id, Model model, Principal principal){
        Story story = storyService.getById(Long.parseLong(id));
        if (story == null) {
            return "redirect:/stories";
        }
        User user = userService.findByLogin(principal.getName());
        if (user == null) {
            return "redirect:/login";
        }
        userStoryMarkService.save(user,story,mark);
        storyService.changeRaiting(story);

        return "redirect:/stories/"+id;
    }

    @GetMapping("/stories/{storyId}/comment/delete/{commentId}")
    public String deleteComment(@PathVariable String storyId,
                                @PathVariable String commentId, Model model,Principal principal){
        try {
            Comment comment = commentService.getById(Long.parseLong(commentId)).get();
            Long loggedInUserId = userService.findByLogin(principal.getName()).getId();
            Long commentAuthorId = comment.getUser().getId();
            if(loggedInUserId.equals(commentAuthorId)) {
                commentService.deleteByComment(comment);
            }
        } catch (Exception ex) {}

        return "redirect:/stories/"+storyId;
    }
}
