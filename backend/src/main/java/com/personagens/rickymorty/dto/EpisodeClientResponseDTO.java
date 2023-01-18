package com.personagens.rickymorty.dto;

import lombok.Data;

@Data
public class EpisodeClientResponseDTO {
    private Integer id;
    private String name;
    private String airDate;
    private String episode;
    private String url;
    private String created;
}
