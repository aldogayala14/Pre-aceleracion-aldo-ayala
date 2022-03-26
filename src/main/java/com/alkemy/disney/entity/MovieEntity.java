package com.alkemy.disney.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "movie")
@Getter
@Setter
@SQLDelete(sql = "UPDATE movie SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class MovieEntity {

    @Id
    @Column
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;
    private String title;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate creationDate;

    private int qualification;



    @ManyToOne
    @JoinColumn(name = "gender_id",insertable = false,updatable = false)
    private GenderEntity gender;

    @Column(name = "gender_id", nullable = false)
    private Long genderId;


    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
        })
    @JoinTable(
        name = "character_movie",
        joinColumns = @JoinColumn (name = "movie_id"),
        inverseJoinColumns =@JoinColumn(name = "character_id"))
    private List<MovieCharacterEntity> characters = new ArrayList<>();

    private boolean deleted = Boolean.FALSE;

    // Add and remove Characters
    public void addCharacter(MovieCharacterEntity characterEntity){ this.characters.add(characterEntity);}

    public void removeCharacter(MovieCharacterEntity characterEntity){ this.characters.remove(characterEntity);}


}
