package com.personagens.rickymorty.dto.episodes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Results {
    private Integer id;
    private String name;
    @JsonProperty("airDate")
    private String airDate;
    private String episode;
    private List<String> characters;
    private String url;
    @JsonProperty("created")
    private String created;
}
