package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class MovieFilterDTO {
    private String name;
    private String date;
    private Set<Long> characters;
    private String order;


    public MovieFilterDTO(String name, String date, Set<Long> characters, String order) {
        this.name = name;
        this.date = date;
        this.characters = characters;
        this.order = order;
    }

    public boolean isASC(){return this.order.compareToIgnoreCase("ASC") == 0;}

    public boolean isDESC(){return this.order.compareToIgnoreCase("DESC") == 0;}
}
