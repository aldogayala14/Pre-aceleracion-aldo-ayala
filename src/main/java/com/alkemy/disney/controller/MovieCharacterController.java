package com.alkemy.disney.controller;

import com.alkemy.disney.dto.MovieCharacterDTO;
import com.alkemy.disney.service.MovieCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("characters")
public class MovieCharacterController {

    @Autowired
    private MovieCharacterService movieCharacterService;

    @GetMapping
    public ResponseEntity<List<MovieCharacterDTO>> getAll(){
        List<MovieCharacterDTO> dtos = movieCharacterService.getAllCharacters();
        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping
    public ResponseEntity<MovieCharacterDTO> save(@RequestBody MovieCharacterDTO movieCharacterDTO){
        MovieCharacterDTO characterSaved = movieCharacterService.save(movieCharacterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        movieCharacterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
