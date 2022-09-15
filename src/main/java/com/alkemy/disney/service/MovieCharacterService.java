package com.alkemy.disney.service;

import com.alkemy.disney.dto.MovieCharacterDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.MovieCharacterEntity;

import java.util.List;
import java.util.Set;

public interface MovieCharacterService {

    MovieCharacterDTO save(MovieCharacterDTO movieCharacterDTO);

    MovieCharacterDTO update(Long id, MovieCharacterDTO movieCharacterDTO);

    List<MovieCharacterDTO> getAllCharacters();


    void delete(Long id);

    MovieCharacterEntity getEntityById(Long idCharacter);

    List<MovieCharacterDTO> getByFilters(String name, String age, String weight, Set<Long> movies, String order);
}
