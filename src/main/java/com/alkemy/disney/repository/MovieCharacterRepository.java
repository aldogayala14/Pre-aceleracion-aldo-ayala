package com.alkemy.disney.repository;

import com.alkemy.disney.entity.MovieCharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCharacterRepository extends JpaRepository<MovieCharacterEntity, Long> {
}
