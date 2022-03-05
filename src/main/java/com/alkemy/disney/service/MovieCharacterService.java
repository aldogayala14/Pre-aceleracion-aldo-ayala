package com.alkemy.disney.service;

import com.alkemy.disney.dto.MovieCharacterDTO;
import com.alkemy.disney.entity.MovieCharacterEntity;

import java.util.List;

public interface MovieCharacterService {

    MovieCharacterDTO save(MovieCharacterDTO movieCharacterDTO);

    MovieCharacterDTO update(Long id, MovieCharacterDTO movieCharacterDTO);

    List<MovieCharacterDTO> getAllCharacters();

    void addMovie(Long id, Long idMovie);

    void removeMovie(Long id, Long idMovie);

    void delete(Long id);

    MovieCharacterEntity getEntityById(Long idCharacter);
}
