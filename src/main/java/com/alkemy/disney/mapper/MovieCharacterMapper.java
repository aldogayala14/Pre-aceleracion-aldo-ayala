package com.alkemy.disney.mapper;


import com.alkemy.disney.dto.MovieCharacterDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.MovieCharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class MovieCharacterMapper {

    @Autowired
    private MovieMapper movieMapper;

    public MovieCharacterEntity characterDTO2Entity(MovieCharacterDTO movieCharacterDTO){
        MovieCharacterEntity movieCharacterEntity = new MovieCharacterEntity();
        movieCharacterEntity.setName(movieCharacterDTO.getName());
        movieCharacterEntity.setImage(movieCharacterDTO.getImage());
        movieCharacterEntity.setAge(movieCharacterDTO.getAge());
        movieCharacterEntity.setHistory(movieCharacterDTO.getHistory());
        movieCharacterEntity.setWeight(movieCharacterDTO.getWeight());
        return movieCharacterEntity;
    }


    public MovieCharacterDTO characterEntity2DTO(MovieCharacterEntity movieCharacterEntity, boolean loadMovies){
        MovieCharacterDTO movieCharacterDTO = new MovieCharacterDTO();
        movieCharacterDTO.setId(movieCharacterEntity.getId());
        movieCharacterDTO.setName(movieCharacterEntity.getName());
        movieCharacterDTO.setImage(movieCharacterEntity.getImage());
        movieCharacterDTO.setAge(movieCharacterEntity.getAge());
        movieCharacterDTO.setHistory(movieCharacterEntity.getHistory());
        movieCharacterDTO.setWeight(movieCharacterEntity.getWeight());
        if(loadMovies){
            List<MovieDTO> movieDTOS =  movieMapper.movieEntity2DTOList(movieCharacterEntity.getMovies(),false);
            movieCharacterDTO.setMovies(movieDTOS);
        }
        return movieCharacterDTO;
    }

    public List<MovieCharacterDTO> characterEntity2DTOList(List<MovieCharacterEntity> entities, boolean loadMovies){
        List<MovieCharacterDTO> dtos = new ArrayList<>();
        for(MovieCharacterEntity entity: entities){
            dtos.add(this.characterEntity2DTO(entity, loadMovies));
        }
        return dtos;
    }

}
