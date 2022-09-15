package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class MovieCharacterFilterDTO {

    private String name;
    private String age;
    private String weight;
    private Set<Long> movies;
    private String order;

    public MovieCharacterFilterDTO(String name, String age, String weight, Set<Long> movies, String order) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.movies = movies;
        this.order = order;
    }

    public boolean isASC(){return this.order.compareToIgnoreCase("ASC") == 0;}

    public boolean isDESC(){return this.order.compareToIgnoreCase("DESC") == 0;}
    
}
