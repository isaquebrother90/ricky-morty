package com.personagens.rickymorty.dto.external;

import lombok.Data;

import java.util.List;

@Data
public class CharacterClientResponseDTO {
    private Integer id;
    private String name;
    private String status;
    private String url;
    private String created;
    private List<EpisodeClientResponseDTO> episode;
}
