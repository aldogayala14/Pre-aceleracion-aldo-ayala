package com.alkemy.disney.controller;

import com.alkemy.disney.dto.GenderDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;


import java.util.List;



class GenderControllerTest {
    private GenderController controller = new GenderController();

    @Test
    void getAll() {
        ResponseEntity<List<GenderDTO>> response = controller.getAll();

    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}