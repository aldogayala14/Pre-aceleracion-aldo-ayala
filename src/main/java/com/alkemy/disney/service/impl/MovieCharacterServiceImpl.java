package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.MovieCharacterDTO;
import com.alkemy.disney.entity.MovieCharacterEntity;
import com.alkemy.disney.mapper.MovieCharacterMapper;
import com.alkemy.disney.repository.MovieCharacterRepository;
import com.alkemy.disney.service.MovieCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieCharacterServiceImpl implements MovieCharacterService {

    @Autowired
    private MovieCharacterRepository movieCharacterRepository;
    @Autowired
    private MovieCharacterMapper movieCharacterMapper;

    @Override
    public MovieCharacterDTO save(MovieCharacterDTO movieCharacterDTO) {
        MovieCharacterEntity entity = movieCharacterMapper.characterDTO2Entity(movieCharacterDTO);
        MovieCharacterEntity characterSaved = movieCharacterRepository.save(entity);
        MovieCharacterDTO result = movieCharacterMapper.characterEntity2DTO(characterSaved);
        return result;
    }

    @Override
    public MovieCharacterDTO update(Long id, MovieCharacterDTO movieCharacterDTO) {
        return null;
    }

    @Override
    public List<MovieCharacterDTO> getAllCharacters() {
       List<MovieCharacterEntity> entities = movieCharacterRepository.findAll();
       List<MovieCharacterDTO> result = movieCharacterMapper.characterEntity2DTOList(entities);
       return result;
    }

    @Override
    public void addMovie(Long id, Long idMovie) {

    }

    @Override
    public void removeMovie(Long id, Long idMovie) {

    }

    @Override
    public void delete(Long id) {
        movieCharacterRepository.deleteById(id);
    }

    @Override
    public MovieCharacterEntity getEntityById(Long idCharacter) {
        return null;
    }
}
