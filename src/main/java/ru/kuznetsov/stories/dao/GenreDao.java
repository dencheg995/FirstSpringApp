package ru.kuznetsov.stories.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kuznetsov.stories.models.Genre;

@Repository
public interface GenreDao extends JpaRepository<Genre,Long> {
    Genre findByName(String name);
}
