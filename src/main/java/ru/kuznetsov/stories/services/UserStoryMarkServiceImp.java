package ru.kuznetsov.stories.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kuznetsov.stories.dao.UserStoryMarkDao;
import ru.kuznetsov.stories.models.Story;
import ru.kuznetsov.stories.models.User;
import ru.kuznetsov.stories.models.UserStoryMark;
import ru.kuznetsov.stories.models.UserStoryMarkPK;

import java.util.Optional;


@Service
public class UserStoryMarkServiceImp implements UserStoryMarkService {
    private UserStoryMarkDao userStoryMarkDao;

    @Autowired
    public UserStoryMarkServiceImp(UserStoryMarkDao userStoryMarkDao) {
        this.userStoryMarkDao = userStoryMarkDao;
    }

    @Override
    public void save(User user, Story story, String mark) {
        userStoryMarkDao.save(new UserStoryMark(user, story, Integer.parseInt(mark)));
    }

    @Override
    public String getMark(User user, Story story) {
        UserStoryMarkPK id = new UserStoryMarkPK(user.getId(), story.getId());
        Optional<UserStoryMark> userStoryMark = userStoryMarkDao.findById(id);
        return userStoryMark.map(storyMark -> storyMark.getMark().toString()).orElse(null);
    }

    @Override
    public Double findSumByStoryId(Long storyId) {
        return userStoryMarkDao.findSumByStoryId(storyId);
    }

    @Override
    public Long findAmountOfMarksByStoryId(Long storyId) {
        return userStoryMarkDao.findAmountOfMarksByStoryId(storyId);
    }
}
