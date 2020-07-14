package ru.kuznetsov.stories.models;

import javax.persistence.*;

@Entity
public class UserStoryMark {

    @EmbeddedId
    private UserStoryMarkPK id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @MapsId("storyId")
    @JoinColumn(name="story_id")
    private Story story;

    @Column(name="mark")
    private Integer mark;


    public UserStoryMark(){}

    public UserStoryMark(User user, Story story, Integer mark) {
        this.id = new UserStoryMarkPK(user.getId(),story.getId());
        this.user = user;
        this.story = story;
        this.mark = mark;
    }

    public UserStoryMarkPK getId() {
        return id;
    }

    public void setId(UserStoryMarkPK id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
