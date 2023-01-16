package com.personagens.rickymorty.repository;

import com.personagens.rickymorty.entity.EpisodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeRepository extends JpaRepository<EpisodeEntity, Integer> {
}
