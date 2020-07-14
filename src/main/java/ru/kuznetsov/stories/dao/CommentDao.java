package ru.kuznetsov.stories.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kuznetsov.stories.models.Comment;

@Repository
public interface CommentDao extends JpaRepository<Comment,Long> {
    Page<Comment> findByStoryId(Long storyId, Pageable pageable);
}
