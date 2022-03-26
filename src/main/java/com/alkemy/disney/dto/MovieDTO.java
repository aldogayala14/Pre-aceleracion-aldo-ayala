package com.alkemy.disney.dto;

import com.alkemy.disney.entity.MovieCharacterEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class MovieDTO {
    private Long id;
    private String image;
    private String title;
    private String creationDate;
    private int qualification;
    private Long genderId;
    private GenderDTO gender;
    private List<MovieCharacterDTO> characters;
}
