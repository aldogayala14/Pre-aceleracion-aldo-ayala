package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class MovieFilterDTO {
    private String name;
    private String date;
    private String idGender;
    private String order;


    public MovieFilterDTO(String name, String date, String idGender, String order) {
        this.name = name;
        this.date = date;
        this.idGender = idGender;
        this.order = order;
    }

    public boolean isASC(){return this.order.compareToIgnoreCase("ASC") == 0;}

    public boolean isDESC(){return this.order.compareToIgnoreCase("DESC") == 0;}
}
