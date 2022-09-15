package com.alkemy.disney.service;

import com.alkemy.disney.dto.GenderDTO;

import java.util.List;

public interface GenderService {

    GenderDTO save(GenderDTO dto);

    List<GenderDTO> getAllGenders();

    void delete(Long id);

    GenderDTO update(Long id, GenderDTO genderDTO);
}
