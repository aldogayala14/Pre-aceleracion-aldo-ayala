package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.MovieCharacterDTO;
import com.alkemy.disney.dto.MovieCharacterFilterDTO;
import com.alkemy.disney.entity.MovieCharacterEntity;
import com.alkemy.disney.exception.ParamNotFound;
import com.alkemy.disney.mapper.MovieCharacterMapper;
import com.alkemy.disney.repository.MovieCharacterRepository;
import com.alkemy.disney.repository.specification.MovieCharacterSpecification;
import com.alkemy.disney.service.MovieCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieCharacterServiceImpl implements MovieCharacterService {

    @Autowired
    private MovieCharacterRepository movieCharacterRepository;
    @Autowired
    private MovieCharacterMapper movieCharacterMapper;
    @Autowired
    private MovieCharacterSpecification movieCharacterSpecification;

    @Override
    public MovieCharacterDTO save(MovieCharacterDTO movieCharacterDTO) {
        MovieCharacterEntity entity = movieCharacterMapper.characterDTO2Entity(movieCharacterDTO,true);
        MovieCharacterEntity characterSaved = movieCharacterRepository.save(entity);
        MovieCharacterDTO result = movieCharacterMapper.characterEntity2DTO(characterSaved,true);
        return result;
    }

    @Override
    public MovieCharacterDTO update(Long id, MovieCharacterDTO movieCharacterDTO) {
        MovieCharacterEntity entity = movieCharacterRepository.findById(id).orElse(null);
        entity.setName(movieCharacterDTO.getName());
        entity.setAge(movieCharacterDTO.getAge());
        entity.setHistory(movieCharacterDTO.getHistory());
        entity.setWeight(movieCharacterDTO.getWeight());
        MovieCharacterEntity entitySaved = movieCharacterRepository.save(entity);
        MovieCharacterDTO result = movieCharacterMapper.characterEntity2DTO(entitySaved,true);
        // entity.setMovies
        return result;
    }

    @Override
    public List<MovieCharacterDTO> getAllCharacters() {
       List<MovieCharacterEntity> entities = movieCharacterRepository.findAll();
       List<MovieCharacterDTO> result = movieCharacterMapper.charactersEntity2DTOList(entities, true);
       return result;
    }

    @Override
    public void delete(Long id) {
        Optional<MovieCharacterEntity> movieCharacter = movieCharacterRepository.findById(id);
        if(!movieCharacter.isPresent()){
            throw new ParamNotFound("Id character not valid");
        }
        movieCharacterRepository.deleteById(id);
    }

    @Override
    public MovieCharacterEntity getEntityById(Long idCharacter) {
        MovieCharacterEntity entity = movieCharacterRepository.getById(idCharacter);
        return  entity;
    }

    @Override
    public List<MovieCharacterDTO> getByFilters(String name, String age, String weight, Set<Long> movies, String order) {
        MovieCharacterFilterDTO movieCharacterFilterDTO = new MovieCharacterFilterDTO(name,age,weight,movies,order);
        List<MovieCharacterEntity> entities = movieCharacterRepository.findAll(movieCharacterSpecification.getByFilters(movieCharacterFilterDTO));
        List<MovieCharacterDTO> result = movieCharacterMapper.charactersEntity2DTOList(entities,true);
        return result;
    }
}
