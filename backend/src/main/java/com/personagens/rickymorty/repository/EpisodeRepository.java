package com.personagens.rickymorty.repository;

import com.personagens.rickymorty.entity.CharacterEntity;
import com.personagens.rickymorty.entity.EpisodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EpisodeRepository extends JpaRepository<EpisodeEntity, Integer> {
    Optional<EpisodeEntity> findByName(String name);
}
