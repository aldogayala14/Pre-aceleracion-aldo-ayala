package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieCharacterDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.MovieCharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



@Component
public class MovieMapper {


    private MovieCharacterMapper movieCharacterMapper;

    @Autowired
    public MovieMapper(@Lazy MovieCharacterMapper movieCharacterMapper){
        this.movieCharacterMapper = movieCharacterMapper;
    }

    public MovieEntity movieDTO2Entity(MovieDTO movieDTO, boolean loadCharacters){
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setImage(movieDTO.getImage());
        movieEntity.setTitle(movieDTO.getTitle());
        movieEntity.setQualification(movieDTO.getQualification());
        movieEntity.setCreationDate(this.string2LocalDate(movieDTO.getCreationDate()));
        // TODO: condicional if load characters
        if(loadCharacters){
            movieEntity.setCharacters(this.movieCharacterMapper.charactersDTO2Entity(movieDTO.getCharacters(),true));
        }
        movieEntity.setGenderId(movieDTO.getGenderId());
        return movieEntity;
    }

    public MovieDTO movieEntity2DTO(MovieEntity movieEntity, boolean loadCharacters){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movieEntity.getId());
        movieDTO.setTitle(movieEntity.getTitle());
        movieDTO.setQualification(movieEntity.getQualification());
        movieDTO.setCreationDate(movieEntity.getCreationDate().toString());
        movieDTO.setImage(movieEntity.getImage());
        movieDTO.setGenderId(movieEntity.getGenderId());
        if(loadCharacters){
            List<MovieCharacterDTO> characterDTOS = this.movieCharacterMapper.charactersEntity2DTOList(movieEntity.getCharacters(),false);
            movieDTO.setCharacters(characterDTOS);
        }
        return movieDTO;
    }

    public List<MovieDTO> movieEntity2DTOList(List<MovieEntity> entities, boolean loadCharacters){
        List<MovieDTO> dtos = new ArrayList<>();
        for(MovieEntity entity : entities){
            dtos.add(this.movieEntity2DTO(entity, loadCharacters));
        }
        return dtos;
    }


    public List<MovieEntity>  movieDTO2EntityList(List<MovieDTO> dtos, boolean loadCharacters){
        List<MovieEntity> entities = new ArrayList<>();
        for(MovieDTO dto : dtos){
            entities.add(this.movieDTO2Entity(dto,loadCharacters));
        }

        return  entities;
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

    public LocalDate string2LocalDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate,formatter);
        return date;
    }
}
