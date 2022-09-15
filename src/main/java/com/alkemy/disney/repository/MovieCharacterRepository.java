package com.alkemy.disney.repository;

import com.alkemy.disney.entity.MovieCharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieCharacterRepository extends JpaRepository<MovieCharacterEntity, Long> {

    List<MovieCharacterEntity> findAll(Specification<MovieCharacterEntity> specification);
}
