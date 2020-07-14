package ru.kuznetsov.stories.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.kuznetsov.stories.models.Comment;
import ru.kuznetsov.stories.models.Story;

import java.util.Optional;

public interface CommentService {
    void save(Comment comment);
    Optional<Comment> getById(Long id);
    void deleteByComment(Comment comment);
    Page<Comment> getAllComments(Story story, Pageable pageable);
}
