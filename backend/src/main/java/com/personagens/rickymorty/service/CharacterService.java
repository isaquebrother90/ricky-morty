package com.personagens.rickymorty.service;

import com.personagens.rickymorty.dto.CharacterRequestDTO;
import com.personagens.rickymorty.dto.CharacterResponseDTO;

import java.util.List;

public interface CharacterService {
    CharacterRequestDTO save(CharacterRequestDTO characterRequestDTO);
    List<CharacterResponseDTO> listAll();
    CharacterResponseDTO searchByName(String name);
}
