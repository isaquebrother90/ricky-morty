package com.personagens.rickymorty.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.personagens.rickymorty.entity.EpisodeEntity;
import lombok.Data;

import java.util.List;

@Data
public class CharacterClientResponseDTO {
    private Integer id;
    private String name;
    private String status;
    private String url;
    private String created;
    @JsonProperty("Episodes")
    private List<EpisodeEntity> episode;
}
