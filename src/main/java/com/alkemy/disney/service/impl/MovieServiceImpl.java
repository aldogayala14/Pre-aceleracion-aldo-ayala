package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.MovieCharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.mapper.MovieMapper;
import com.alkemy.disney.repository.MovieCharacterRepository;
import com.alkemy.disney.repository.MovieRepository;
import com.alkemy.disney.service.MovieCharacterService;
import com.alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieCharacterService characterService;
    @Autowired
    private MovieMapper movieMapper;

    @Override
    public MovieDTO save(MovieDTO movieDTO) {
        MovieEntity entity = movieMapper.movieDTO2Entity(movieDTO);
        MovieEntity movieSaved = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(movieSaved);
        return result;
    }

    @Override
    public List<MovieBasicDTO> getAllMovies() {
        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieBasicDTO> result = movieMapper.movieEntity2DTOBasicList(entities);
        return result;
    }

    @Override
    public MovieDTO getDetailsById(Long id) {
        MovieEntity movie = movieRepository.findById(id).orElse(null);
        MovieDTO result = movieMapper.movieEntity2DTO(movie);
        return result;
    }


    //Delete Movie
    @Override
    public void delete(Long id) {
       this.movieRepository.deleteById(id);
    }

    @Override
    public MovieDTO update(Long id, MovieDTO movieDTO) {
        return null;
    }

    @Override
    public void addCharacter(Long id, Long idCharacter) {
         MovieEntity entity = this.movieRepository.getById(id);
         entity.getCharacters().size();
         MovieCharacterEntity characterEntity = this.characterService.getEntityById(idCharacter);
         entity.addCharacter(characterEntity);
         this.movieRepository.save(entity);
    }

    @Override
    public void removeCharacter(Long id, Long idCharacter) {
            MovieEntity entity = this.movieRepository.getById(id);
            entity.getCharacters().size();
            MovieCharacterEntity characterEntity = this.characterService.getEntityById(idCharacter);
            entity.removeCharacter(characterEntity);
            this.movieRepository.save(entity);

    }
}
