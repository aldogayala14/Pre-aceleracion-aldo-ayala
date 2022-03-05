package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.GenderDTO;
import com.alkemy.disney.entity.GenderEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenderMapper {

    public GenderEntity genderDTO2Entity(GenderDTO genderDTO){
        GenderEntity genderEntity = new GenderEntity();
        genderEntity.setImage(genderDTO.getImage());
        genderEntity.setName(genderDTO.getName());
        return genderEntity;
    }

    public GenderDTO genderEntity2DTO(GenderEntity genderEntity){
        GenderDTO genderDTO = new GenderDTO();
        genderDTO.setId(genderEntity.getId());
        genderDTO.setImage(genderEntity.getImage());
        genderDTO.setName(genderEntity.getName());
        return genderDTO;
    }

    public List<GenderDTO> genderEntity2DTOList(List<GenderEntity> entities){
        List<GenderDTO> dtos = new ArrayList<>();
        for(GenderEntity entity: entities){
            dtos.add(genderEntity2DTO(entity));
        }
        return dtos;
    }
}
