package com.personagens.rickymorty.service;

import com.personagens.rickymorty.dto.external.CharacterClientResponseDTO;
import com.personagens.rickymorty.entity.CharacterEntity;
import com.personagens.rickymorty.entity.EpisodeEntity;

import java.util.List;

public interface CharacterService {
    CharacterEntity saveCharacter(CharacterEntity characterEntity);
    EpisodeEntity saveEpisode(EpisodeEntity episodeEntity);
    List<CharacterClientResponseDTO> listAll();
    CharacterClientResponseDTO searchByName(String name);
}
