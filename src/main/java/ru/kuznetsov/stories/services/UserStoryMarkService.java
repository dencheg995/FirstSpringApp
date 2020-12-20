package ru.kuznetsov.stories.services;

import ru.kuznetsov.stories.models.Story;
import ru.kuznetsov.stories.models.User;


public interface UserStoryMarkService {
    void save(User user, Story story, String mark);
    String getMark(User user, Story story);
    Double findSumByStoryId(Long storyId);
    Long findAmountOfMarksByStoryId(Long storyId);

}
