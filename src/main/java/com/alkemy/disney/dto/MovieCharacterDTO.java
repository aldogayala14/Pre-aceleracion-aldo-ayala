package com.alkemy.disney.dto;

import com.alkemy.disney.entity.MovieEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MovieCharacterDTO {
    private Long id;
    private String image;
    private String name;
    private int age;
    private double  weight;
    private String history;
    private List<MovieDTO> movies;
}
