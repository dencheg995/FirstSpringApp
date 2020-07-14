package ru.kuznetsov.stories.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import ru.kuznetsov.stories.models.Comment;
import ru.kuznetsov.stories.models.Story;

import java.util.List;
import java.util.Set;

public interface StoryService {
    void addGenresToStory(Story story, List<String> genresId);
    void save(Story story);
    Page<Story> getAllStories(Pageable pageable);
    Page<Story> findFiltered(String authorLogin, String storyTitle, String genreId, Pageable pageable);
    Story getById(Long id);
    Story getByTitle(String title);
    boolean validatePublishForm(String title, String shortDesc, String fullText, List<String> genres, Model model);
    void changeRaiting(Story story);
}
