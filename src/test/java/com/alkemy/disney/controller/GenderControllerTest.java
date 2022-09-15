package com.alkemy.disney.controller;



import com.alkemy.disney.dto.GenderDTO;
import com.alkemy.disney.entity.GenderEntity;
import com.alkemy.disney.mapper.GenderMapper;
import com.alkemy.disney.service.GenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Arrays;
import java.util.List;


import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;



@WebMvcTest(GenderController.class)
class GenderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GenderMapper genderMapper;

    @MockBean
    private GenderService genderService;


    @Test
    void getAll() throws Exception {
        GenderEntity drama = new GenderEntity();
        drama.setId(1L);
        drama.setName("drama");
        drama.setImage("/img/drama.jpg");
        drama.setDeleted(false);

        GenderDTO dramaDTO = this.genderMapper.genderEntity2DTO(drama);

        List<GenderDTO> listGender =
                Arrays.asList(dramaDTO);

        when(genderService.getAllGenders()).thenReturn(listGender);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/patient")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect((ResultMatcher) jsonPath("$[0].name", is("drama")));

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