package ru.kuznetsov.stories.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kuznetsov.stories.dao.CommentDao;
import ru.kuznetsov.stories.models.Comment;
import ru.kuznetsov.stories.models.Story;

import java.util.Date;
import java.util.Optional;

@Service
public class CommentServiceImp implements CommentService {
    private CommentDao commentDao;

    @Autowired
    public CommentServiceImp(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
    @Override
    public void save(Comment comment) {
        comment.setDate(new Date());
        commentDao.save(comment);
    }

    @Override
    public Optional<Comment> getById(Long id) {
        return commentDao.findById(id);
    }

    @Override
    public void deleteByComment(Comment comment) {
        commentDao.delete(comment);
    }

    @Override
    public Page<Comment> getAllComments(Story story, Pageable pageable) {
        return commentDao.findByStoryId(story.getId(), pageable);
    }
}
