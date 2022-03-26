package com.alkemy.disney.service;

import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;

import java.util.List;
import java.util.Set;

public interface MovieService {

    MovieDTO save(MovieDTO movieDTO);

    List<MovieBasicDTO> getAllMovies();

    MovieDTO getDetailsById(Long id);

    void delete(Long id);

    MovieDTO update(Long id, MovieDTO movieDTO);

    void addCharacter(Long id, Long idCharacter);

    void removeCharacter(Long id, Long idCharacter);

    List<MovieDTO> getByFilters(String name, String date, Set<Long> characters, String order);
}
