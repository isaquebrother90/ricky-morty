package com.personagens.rickymorty.service.impl;

import com.personagens.rickymorty.dto.CharacterRequestDTO;
import com.personagens.rickymorty.dto.CharacterResponseDTO;
import com.personagens.rickymorty.repository.CharacterRepository;
import com.personagens.rickymorty.service.CharacterService;
import com.personagens.rickymorty.utils.CharacterAlias;
import entity.CharacterEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CharacterServiceImpl implements CharacterService {
    private CharacterRepository characterRepository;

    private ModelMapper modelMapper;

    public CharacterServiceImpl(CharacterRepository characterRepository, ModelMapper modelMapper) {
        this.characterRepository = characterRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CharacterRequestDTO save(CharacterRequestDTO characterRequestDTO) {
        CharacterEntity characterEntity = characterRepository.save(toCharacterEntity(characterRequestDTO));
        return toCharacterRequestDTO(characterEntity);
    }
    @Override
    public List<CharacterResponseDTO> listAll() {
        return characterRepository.findAll().stream().map(this::toCharacterResponseDTO).collect(Collectors.toList());
    }
    @Override
    public CharacterResponseDTO searchByName(String name) {

        String aliasConverted = CharacterAlias.aliasConversor(name);
        Optional<CharacterEntity> characterEntity = characterRepository.findCharacterByName(aliasConverted);

        if (!characterEntity.isPresent()) {
            //throw new CharacterNotFoundException("Character not found.");
            System.out.println("Não está presente!");
        }

        return toCharacterResponseDTO(characterEntity);
    }

    //Mappers
    private CharacterRequestDTO toCharacterRequestDTO(CharacterEntity characterEntity) {
        return modelMapper.map(characterEntity, CharacterRequestDTO.class);
    }

    private CharacterEntity toCharacterEntity(CharacterRequestDTO characterRequestDTO) {
        return modelMapper.map(characterRequestDTO, CharacterEntity.class);
    }

    private CharacterResponseDTO toCharacterResponseDTO(Optional<CharacterEntity> characterEntity) {
        return modelMapper.map(characterEntity, CharacterResponseDTO.class);
    }
    private CharacterResponseDTO toCharacterResponseDTO(CharacterEntity characterEntity) {
        return modelMapper.map(characterEntity, CharacterResponseDTO.class);
    }
}
