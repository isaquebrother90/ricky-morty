package com.personagens.rickymorty.dto.external.episodes;

import com.personagens.rickymorty.dto.external.characters.Info;
import com.personagens.rickymorty.entity.EpisodeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpisodesApiResponse {

    private Info info;
    private List<Results> results;

    public List<EpisodeEntity> responseToEntity() {
        List<EpisodeEntity> episodeEntityList = new ArrayList<>();
        for (Results rts : results) {
            EpisodeEntity episodeEntity = EpisodeEntity.builder()
                    .name(rts.getName())
                    .airDate(rts.getAirDate())
                    .episode(rts.getEpisode())
                    .url(rts.getUrl())
                    .created(rts.getCreated())
                    .build();
            episodeEntityList.add(episodeEntity);
        }
        return episodeEntityList;
    }
}
