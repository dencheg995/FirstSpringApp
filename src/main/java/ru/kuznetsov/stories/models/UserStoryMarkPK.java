package ru.kuznetsov.stories.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserStoryMarkPK implements Serializable {

    @Column(name="user_id")
    private Long userId;

    @Column(name="story_id")
    private Long storyId;

    public UserStoryMarkPK(){}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStoryId() {
        return storyId;
    }

    public void setStoryId(Long storyId) {
        this.storyId = storyId;
    }

    public UserStoryMarkPK(Long userId, Long storyId){
        this.userId = userId;
        this.storyId = storyId;
    }
}
