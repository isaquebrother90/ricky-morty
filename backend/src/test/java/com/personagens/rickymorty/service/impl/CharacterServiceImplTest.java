package com.personagens.rickymorty.service.impl;

import com.personagens.rickymorty.dto.characters.CharactersApiResponse;
import com.personagens.rickymorty.dto.characters.Info;
import com.personagens.rickymorty.dto.characters.Results;
import com.personagens.rickymorty.dto.episodes.EpisodesApiResponse;
import com.personagens.rickymorty.entity.CharacterEntity;
import com.personagens.rickymorty.entity.EpisodeEntity;
import com.personagens.rickymorty.repository.CharacterRepository;
import com.personagens.rickymorty.repository.EpisodeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CharacterServiceImplTest {
    @Mock
    private CharacterRepository characterRepository;
    @Mock
    private EpisodeRepository episodeRepository;
    @InjectMocks
    private CharacterServiceImpl characterService;
    private CharacterEntity characterEntity;
    private EpisodeEntity episodeEntity;
    private List<CharacterEntity> characterList;
    private List<EpisodeEntity> episodeList;
    private CharactersApiResponse apiResponse;
    @Mock
    private RestTemplate restTemplate;
    private EpisodesApiResponse episodesData;
    private CharactersApiResponse charactersData;

    @Before
    public void setUp() {
        episodesData = new EpisodesApiResponse();
        charactersData = new CharactersApiResponse();
        charactersData.setInfo(new Info());
        charactersData.setResults(new ArrayList<Results>());
    }

    @Test
    public void testSaveCharacter() {
        when(characterRepository.save(characterEntity)).thenReturn(characterEntity);
        CharacterEntity savedCharacter = characterService.saveCharacter(characterEntity);
        assertEquals(savedCharacter, characterEntity);
    }

    @Test
    public void testSaveEpisode() {
        when(episodeRepository.save(episodeEntity)).thenReturn(episodeEntity);
        EpisodeEntity savedEpisode = characterService.saveEpisode(episodeEntity);
        assertEquals(savedEpisode, episodeEntity);
    }

    @Test
    public void testSearchCharactersInRickyAndMortyApi() {
        String url = "https://rickandmortyapi.com/api/character";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<CharactersApiResponse> response = new ResponseEntity<>(charactersData, HttpStatus.OK);
        CharactersApiResponse characters = characterService.searchCharactersInRickyAndMortyApi();
        assertEquals(response.getStatusCodeValue(), 200);
    }

    @Test
    public void testSearchEpisodesInRickAndMortyApi() {
        String url = "https://rickandmortyapi.com/api/episode";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<EpisodesApiResponse> response = new ResponseEntity<>(episodesData, HttpStatus.OK);
        assertEquals(response.getStatusCodeValue(), 200);
    }
}
