package ru.kuznetsov.stories.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kuznetsov.stories.models.Story;

@Repository
public interface StoryDao extends JpaRepository<Story,Long> {
    Page<Story> findAll(Pageable pageable);


    @Query(value = "Select distinct s.id, s.marks_amount, s.full_text, " +
            " s.publish_date, s.raiting, s.short_desc, s.title, s.author_id" +
            " from story s join user u on s.author_id = u.id" +
            " join story_genre sg on s.id = sg.story_id " +
            "where u.login LIKE ?1 AND s.id LIKE ?2 AND sg.genre_id LIKE ?3 ORDER BY s.id",
            countQuery = "Select count(distinct s.id)" +
                    " from story s join user u on s.author_id = u.id" +
                    " join story_genre sg on s.id = sg.story_id " +
                    "where u.login LIKE ?1 AND s.id LIKE ?2 AND sg.genre_id LIKE ?3 ",
            nativeQuery = true)
    Page<Story> findFiltered(String authorLogin, String storyId, String genreId,Pageable pageable);

    @Query(value = "select * from story where lower(title)=lower(?1)", nativeQuery = true)
    Story findByTitle(String title);
}
