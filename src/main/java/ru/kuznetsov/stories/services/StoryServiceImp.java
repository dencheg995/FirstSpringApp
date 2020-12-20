package ru.kuznetsov.stories.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.kuznetsov.stories.dao.StoryDao;
import ru.kuznetsov.stories.models.Genre;
import ru.kuznetsov.stories.models.Story;

import java.util.*;

@Service
public class StoryServiceImp implements StoryService {
    private StoryDao storyDao;
    private GenreService genreService;
    private UserStoryMarkService userStoryMarkService;

    @Autowired
    public StoryServiceImp(StoryDao storyDao, GenreService genreService,
                           UserStoryMarkService userStoryMarkService) {
        this.storyDao = storyDao;
        this.genreService = genreService;
        this.userStoryMarkService = userStoryMarkService;
    }

    @Override
    public void addGenresToStory(Story story, List<String> genresId) {
        Set<Genre> genreSet = story.getGenres();
        for (String genreId : genresId) {
            genreSet.add(genreService.getById(Long.parseLong(genreId)));
        }
    }

    @Override
    public void save(Story story) {
        story.setAmountOfMarks(0L);
        story.setRating(0d);
        story.setPublishDate(new Date());
        storyDao.save(story);
    }

    @Override
    public Page<Story> getAllStories(Pageable pageable) {
        return storyDao.findAll(pageable);
    }

    @Override
    public Page<Story> findFiltered(String authorLogin, String storyTitle, String genreId, Pageable pageable) {
        return storyDao.findFiltered(authorLogin,storyTitle,genreId,pageable);
    }


    @Override
    public Story getById(Long id) {
        Optional<Story> story = storyDao.findById(id);
        return story.orElse(null);
    }

    @Override
    public Story getByTitle(String title) {
        return storyDao.getFirstByTitle(title).orElse(null);
    }

    @Override
    public boolean validatePublishForm(String title, String shortDesc,
                                       String fullText, List<String> genres, Model model) {
        boolean hasErrors = false;
        if(title.equals("")){
            model.addAttribute("errorTitle", "Введите название");
            hasErrors = true;
        }
        if(shortDesc.equals("")){
            model.addAttribute("errorDesc", "Опишите Ваш рассказ");
            hasErrors = true;
        }
        if(fullText.equals("")){
            model.addAttribute("errorStory","Введите текст рассказа");
            hasErrors = true;
        }
        if(genres == null){
            model.addAttribute("errorGenre", "Выберите хотя бы один жанр");
            hasErrors = true;
        }
        return hasErrors;
    }


    @Override
    public void changeRating(Story story) {
        Long storyId = story.getId();
        Double sum = userStoryMarkService.findSumByStoryId(storyId);
        Long amount = userStoryMarkService.findAmountOfMarksByStoryId(storyId);
        System.out.println(sum + " " + amount);
        story.setRating(sum/amount);
        story.setAmountOfMarks(amount);
        storyDao.save(story);
    }

    @Override
    public Page<Story> getBestStories(Pageable pageable) {
        return storyDao.getBestStories(pageable);
    }

    private void filterByAuthor(List<Story> stories, String author) {
        stories.removeIf(story -> !story.getAuthor().getLogin().toLowerCase().equals(author));
    }

    private void filterByTitle(List<Story> stories, String title) {
        stories.removeIf(story -> !story.getTitle().toLowerCase().equals(title));
    }

    private void filterByGenre(List<Story> stories, String genre) {
        stories.removeIf(story -> !story.getGenres().contains(genreService.getById(Long.parseLong(genre))));
    }
}
