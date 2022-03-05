package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class MovieMapper {

    public MovieEntity movieDTO2Entity(MovieDTO movieDTO){
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setImage(movieDTO.getImage());
        movieEntity.setTitle(movieDTO.getTitle());
        movieEntity.setQualification(movieDTO.getQualification());
        movieEntity.setCreationDate(movieDTO.getCreationDate());
        movieEntity.setGenderId(movieDTO.getGenderId());
        return movieEntity;
    }

    public MovieDTO movieEntity2DTO(MovieEntity movieEntity){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movieEntity.getId());
        movieDTO.setTitle(movieEntity.getTitle());
        movieDTO.setQualification(movieEntity.getQualification());
        movieDTO.setCreationDate(movieEntity.getCreationDate());
        movieDTO.setImage(movieEntity.getImage());
        movieDTO.setGenderId(movieEntity.getGenderId());
        return movieDTO;
    }

    public List<MovieDTO> movieEntity2DTOList(List<MovieEntity> entities){
        List<MovieDTO> dtos = new ArrayList<>();
        for(MovieEntity entity : entities){
            dtos.add(this.movieEntity2DTO(entity));
        }
        return dtos;
    }

    //Basic DTOS
    public MovieBasicDTO movieEntity2DTOBasic(MovieEntity movieEntity){
        MovieBasicDTO movieBasicDTO = new MovieBasicDTO();
        movieBasicDTO.setId(movieEntity.getId());
        movieBasicDTO.setTitle(movieEntity.getTitle());
        movieBasicDTO.setImage(movieEntity.getImage());
        return movieBasicDTO;
    }

    public List<MovieBasicDTO> movieEntity2DTOBasicList(List<MovieEntity> entities){
        List<MovieBasicDTO> basicDTOS = new ArrayList<>();
        for(MovieEntity entity: entities){
            basicDTOS.add(this.movieEntity2DTOBasic(entity));
        }

        return basicDTOS;
    }
}
