package com.alkemy.disney.dto;

import com.alkemy.disney.entity.MovieCharacterEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class MovieDTO {
    private Long id;
    private String image;
    private String title;
    private LocalDate creationDate;
    private int qualification;
    private Long genderId;
    private Set<MovieCharacterDTO> characters;
}
