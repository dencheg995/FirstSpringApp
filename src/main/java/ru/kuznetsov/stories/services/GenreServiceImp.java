package ru.kuznetsov.stories.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kuznetsov.stories.dao.GenreDao;
import ru.kuznetsov.stories.models.Genre;

import java.util.List;

@Service
public class GenreServiceImp implements GenreService {

    private GenreDao genreDao;

    @Autowired
    public GenreServiceImp(GenreDao genreDao){
        this.genreDao = genreDao;
    }
    @Override
    public List<Genre> getAllGenres() {
        return genreDao.findAll();
    }

    @Override
    public Genre getById(Long id) {
        return genreDao.getOne(id);
    }
}
