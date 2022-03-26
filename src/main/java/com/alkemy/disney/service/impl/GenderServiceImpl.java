package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.GenderDTO;
import com.alkemy.disney.entity.GenderEntity;
import com.alkemy.disney.mapper.GenderMapper;
import com.alkemy.disney.repository.GenderRepository;
import com.alkemy.disney.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderServiceImpl implements GenderService {

    @Autowired
    private GenderMapper genderMapper;
    @Autowired
    private GenderRepository genderRepository;

    @Override
    public GenderDTO save(GenderDTO dto) {
        GenderEntity entity = genderMapper.genderDTO2Entity(dto);
        GenderEntity genderSaved = genderRepository.save(entity);
        GenderDTO result = genderMapper.genderEntity2DTO(genderSaved);
        return result;
    }

    @Override
    public List<GenderDTO> getAllGenders() {
        List<GenderEntity> entities = genderRepository.findAll();
        List<GenderDTO> result = genderMapper.genderEntity2DTOList(entities);
        return result;
    }

    @Override
    public void delete(Long id) {
        genderRepository.deleteById(id);
    }
}
