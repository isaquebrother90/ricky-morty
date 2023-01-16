package com.personagens.rickymorty.dto.external.characters;

import com.personagens.rickymorty.entity.CharacterEntity;
import com.personagens.rickymorty.entity.EpisodeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharactersApiResponse {

    private Info info;
    private List<Results> results;

    public List<CharacterEntity> responseToEntity(com.personagens.rickymorty.dto.external.episodes.Results epResults) {
        CharacterEntity character = CharacterEntity.builder()
                .name(results.get(0).getName())
                .status(results.get(0).getStatus())
                .url(results.get(0).getUrl())
                .created(results.get(0).getCreated())
                .episode(episodesByCharacter(epResults))
                .build();
        List<CharacterEntity> characterEntities = new ArrayList<>();
        characterEntities.add(character);
        return characterEntities;
    }

    public List<EpisodeEntity> episodesByCharacter(com.personagens.rickymorty.dto.external.episodes.Results eresult) {
        List<String> characterEpisodes = results.stream().map(Results::getEpisode)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        List<EpisodeEntity> entities = new ArrayList<>();//
        for (String ce : characterEpisodes) {
            if (ce.equals(eresult.getUrl())) {

                EpisodeEntity epEntity = EpisodeEntity.builder()
                        .name(eresult.getName())
                        .airDate(eresult.getAirDate())
                        .episode(eresult.getEpisode())
                        .url(eresult.getUrl())
                        .created(eresult.getCreated())
                        .build();

                entities.add(epEntity);
            }
        }
        return entities;
    }
}
