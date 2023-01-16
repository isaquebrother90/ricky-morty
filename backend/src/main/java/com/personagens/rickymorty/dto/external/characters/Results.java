package com.personagens.rickymorty.dto.external.characters;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.util.List;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Results {
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private OriginCharacter originCharacter;
    private LocationCharacter locationCharacter;
    private String image;
    private List<String> episode;
    private String url;
    private String created;
}
