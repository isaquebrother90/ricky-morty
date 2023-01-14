package com.personagens.rickymorty.service;

import com.personagens.rickymorty.dto.EpisodeRequestDTO;
import com.personagens.rickymorty.dto.EpisodeResponseDTO;

import java.util.List;

public interface EpisodeService {
    EpisodeRequestDTO save(EpisodeRequestDTO episodeRequestDTO);
    List<EpisodeResponseDTO> listAll();
}
