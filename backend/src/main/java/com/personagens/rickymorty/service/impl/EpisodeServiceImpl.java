package com.personagens.rickymorty.service.impl;

import com.personagens.rickymorty.dto.EpisodeRequestDTO;
import com.personagens.rickymorty.dto.EpisodeResponseDTO;
import com.personagens.rickymorty.repository.EpisodeRepository;
import com.personagens.rickymorty.service.EpisodeService;
import entity.EpisodeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EpisodeServiceImpl implements EpisodeService {
    private EpisodeRepository episodeRepository;

    private ModelMapper modelMapper;

    public EpisodeServiceImpl(EpisodeRepository episodeRepository, ModelMapper modelMapper) {
        this.episodeRepository = episodeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EpisodeRequestDTO save(EpisodeRequestDTO episodeRequestDTO) {
        EpisodeEntity episodeEntity = episodeRepository.save(toEpisodeEntity(episodeRequestDTO));
        return toEpisodeRequestDTO(episodeEntity);
    }
    @Override
    public List<EpisodeResponseDTO> listAll() {
        return episodeRepository.findAll().stream().map(this::toEpisodeResponseDTO).collect(Collectors.toList());
    }

    //Mappers
    private EpisodeRequestDTO toEpisodeRequestDTO(EpisodeEntity episodeEntity) {
        return modelMapper.map(episodeEntity, EpisodeRequestDTO.class);
    }

    private EpisodeEntity toEpisodeEntity(EpisodeRequestDTO episodeRequestDTO) {
        return modelMapper.map(episodeRequestDTO, EpisodeEntity.class);
    }

    private EpisodeResponseDTO toEpisodeResponseDTO(Optional<EpisodeEntity> episodeEntity) {
        return modelMapper.map(episodeEntity, EpisodeResponseDTO.class);
    }
    private EpisodeResponseDTO toEpisodeResponseDTO(EpisodeEntity episodeEntity) {
        return modelMapper.map(episodeEntity, EpisodeResponseDTO.class);
    }
}
