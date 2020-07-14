package ru.kuznetsov.stories.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type="text")
    private String fullText;

    private String title;

    @Type(type="text")
    private String shortDesc;

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    @Column(nullable = true)
    private Double raiting;

    @Column(nullable = true, name="marks_amount")
    private Long amountOfMarks;

    @Column(name="publish_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date publishDate;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany
    @JoinTable(name="story_genre",
            joinColumns = {@JoinColumn(name="story_id")},
            inverseJoinColumns = {@JoinColumn(name="genre_id")})
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "story")
    private Set<UserStoryMark> marks = new HashSet<>();

    public Story(){}

    public Story(String title, String shortDesc, String fullText, User author){
        this.title = title;
        this.shortDesc = shortDesc;
        this.fullText = fullText;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String full_text) {
        this.fullText = full_text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String short_desc) {
        this.shortDesc = short_desc;
    }

    public Double getRaiting() {
        return raiting;
    }

    public void setRaiting(Double raiting) {
        this.raiting = raiting;
    }

    public Long getAmountOfMarks() {
        return amountOfMarks;
    }

    public void setAmountOfMarks(Long amountOfMarks) {
        this.amountOfMarks = amountOfMarks;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

}
