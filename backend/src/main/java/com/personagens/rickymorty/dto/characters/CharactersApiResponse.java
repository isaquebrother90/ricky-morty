package com.personagens.rickymorty.dto.characters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharactersApiResponse {

    private Info info;
    private List<Results> results;

}
