package com.personagens.rickymorty.dto.external.characters;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Info {
    private Integer count;
    private Integer pages;
    private String next;
    private String prev;
}
