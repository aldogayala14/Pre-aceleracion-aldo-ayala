package com.alkemy.disney.controller;

import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<List<MovieBasicDTO>> getAll(){
        List<MovieBasicDTO> dtos = movieService.getAllMovies();
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getDetailsById(@PathVariable Long id){
        MovieDTO movie = movieService.getDetailsById(id);
        return ResponseEntity.ok().body(movie);
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getDetailsByFilters(@RequestParam(required = false) String name,
                                                              @RequestParam(required = false) String date,
                                                              @RequestParam(required = false) String gender,
                                                              @RequestParam(required = false , defaultValue = "ASC") String order)
    {
        List<MovieDTO> movieDTOS = movieService.getByFilters(name,date,gender,order);
        return ResponseEntity.ok(movieDTOS);

    }

    @PostMapping
    public ResponseEntity<MovieDTO> save(@Valid @RequestBody MovieDTO movieDTO){
       MovieDTO movieSaved = movieService.save(movieDTO);
       return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id,@Valid @RequestBody MovieDTO movieDTO){
        MovieDTO result = movieService.update(id,movieDTO);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/{id}/character/{idCharacter}")
    public ResponseEntity<Void> addCharacter(@PathVariable Long id, @PathVariable Long idCharacter){
        this.movieService.addCharacter(id, idCharacter);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}/character/{idCharacter}")
    public ResponseEntity<Void> removeCharacter(@PathVariable Long id, @PathVariable Long idCharacter){
        this.movieService.removeCharacter(id, idCharacter);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
