package com.alkemy.disney.entity;



import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie_character")
@Getter
@Setter
@SQLDelete(sql = "UPDATE movie_character SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class MovieCharacterEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    private String image;
    private String name;
    private int age;
    private double weight;
    private String history;


    @ManyToMany(mappedBy = "characters")
    private List<MovieEntity> movies = new ArrayList<>();

    private boolean deleted = Boolean.FALSE;


    //add and remove Movies
    public void addMovies(MovieEntity movie){ this.movies.add(movie);};

    public void removeMovies(MovieEntity movie){ this.movies.remove(movie);};



}
