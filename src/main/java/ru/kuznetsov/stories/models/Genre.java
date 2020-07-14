package ru.kuznetsov.stories.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Story> getStories() {
        return stories;
    }

    public void setStories(Set<Story> stories) {
        this.stories = stories;
    }

    @ManyToMany(mappedBy = "genres")
    Set<Story> stories = new HashSet<>();

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(obj instanceof Genre){
            Genre that = (Genre) obj;
            return this.id == that.id && this.name.equals(that.name);
        }
        return false;
    }
}
