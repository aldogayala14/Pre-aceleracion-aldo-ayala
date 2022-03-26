package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.dto.MovieFilterDTO;
import com.alkemy.disney.entity.MovieCharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.exception.ParamNotFound;
import com.alkemy.disney.mapper.MovieMapper;
import com.alkemy.disney.repository.MovieRepository;
import com.alkemy.disney.repository.specification.MovieSpecification;
import com.alkemy.disney.service.MovieCharacterService;
import com.alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {

    //Repo
    private MovieRepository movieRepository;
    private MovieSpecification movieSpecification;

    //Mapper
    private MovieMapper movieMapper;

   //Service
    private MovieCharacterService characterService;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository,
                            MovieSpecification movieSpecification,
                            MovieMapper movieMapper,
                            MovieCharacterService characterService) {
        this.movieRepository = movieRepository;
        this.movieSpecification = movieSpecification;
        this.movieMapper = movieMapper;
        this.characterService = characterService;
    }

    @Override
    public MovieDTO save(MovieDTO movieDTO) {
        MovieEntity entity = movieMapper.movieDTO2Entity(movieDTO);
        MovieEntity movieSaved = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(movieSaved,true);
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
        Optional<MovieEntity> movie = movieRepository.findById(id);
        if(!movie.isPresent()){
            throw new ParamNotFound("Id movie not valid");
        }
        MovieDTO result = movieMapper.movieEntity2DTO(movie.get(),true);
        return result;
    }


    //Delete Movie
    @Override
    public void delete(Long id) {
       this.movieRepository.deleteById(id);
    }

    @Override
    public MovieDTO update(Long id, MovieDTO movieDTO) {
        MovieEntity entity = movieRepository.findById(id).orElse(null);
        entity.setImage(movieDTO.getImage());
        entity.setTitle(movieDTO.getTitle());
        entity.setCreationDate(LocalDate.parse(movieDTO.getCreationDate()));
        entity.setQualification(movieDTO.getQualification());
        MovieEntity entitySaved = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(entitySaved,true);
       // entity.set
        return result;
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

    @Override
    public List<MovieDTO> getByFilters(String name, String date, Set<Long> characters, String order) {
        MovieFilterDTO movieFilterDTO = new MovieFilterDTO(name,date,characters,order);
        List<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(movieFilterDTO));
        List<MovieDTO> movieDTOS = movieMapper.movieEntity2DTOList(entities,true);
        return movieDTOS;
    }
}
