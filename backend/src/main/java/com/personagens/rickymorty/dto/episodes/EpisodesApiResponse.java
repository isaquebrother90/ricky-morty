package com.personagens.rickymorty.dto.episodes;

import com.personagens.rickymorty.dto.characters.Info;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpisodesApiResponse {

    private Info info;
    private List<Results> results;

}
