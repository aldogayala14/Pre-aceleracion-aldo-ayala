package com.alkemy.disney.controller;

import com.alkemy.disney.dto.MovieCharacterDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.service.MovieCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("characters")
public class MovieCharacterController {

    @Autowired
    private MovieCharacterService movieCharacterService;

    @GetMapping("/all")
    public ResponseEntity<List<MovieCharacterDTO>> getAll(){
        List<MovieCharacterDTO> dtos = movieCharacterService.getAllCharacters();
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping
    public ResponseEntity<List<MovieCharacterDTO>> getDetailsByFilters(@RequestParam(required = false) String name,
                                                              @RequestParam(required = false) String age,
                                                              @RequestParam(required = false) String weight,
                                                              @RequestParam(required = false) Set<Long> movies,
                                                              @RequestParam(required = false , defaultValue = "ASC") String order)
    {
        List<MovieCharacterDTO> movieCharacterDTOS = movieCharacterService.getByFilters(name,age,weight,movies,order);
        return ResponseEntity.ok(movieCharacterDTOS);

    }

    @PostMapping
    public ResponseEntity<MovieCharacterDTO> save(@Valid @RequestBody MovieCharacterDTO movieCharacterDTO){
        MovieCharacterDTO characterSaved = movieCharacterService.save(movieCharacterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        movieCharacterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieCharacterDTO> update(@PathVariable Long id,@Valid @RequestBody MovieCharacterDTO movieCharacterDTO){
        MovieCharacterDTO result = movieCharacterService.update(id,movieCharacterDTO);
        return ResponseEntity.ok().body(result);
    }
}
