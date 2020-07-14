package ru.kuznetsov.stories.services;

import org.springframework.stereotype.Component;
import ru.kuznetsov.stories.models.Genre;

import java.util.List;

@Component
public interface GenreService {
    List<Genre> getAllGenres();
    Genre getById(Long id);
    Genre getByName(String name);
}
