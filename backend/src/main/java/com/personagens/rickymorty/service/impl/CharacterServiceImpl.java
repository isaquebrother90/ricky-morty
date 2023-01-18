package com.personagens.rickymorty.service.impl;

import com.personagens.rickymorty.dto.CharacterClientResponseDTO;
import com.personagens.rickymorty.dto.characters.CharactersApiResponse;
import com.personagens.rickymorty.dto.characters.Results;
import com.personagens.rickymorty.dto.episodes.EpisodesApiResponse;
import com.personagens.rickymorty.entity.CharacterEntity;
import com.personagens.rickymorty.entity.EpisodeEntity;
import com.personagens.rickymorty.exception.ContentTypeException;
import com.personagens.rickymorty.repository.CharacterRepository;
import com.personagens.rickymorty.repository.EpisodeRepository;
import com.personagens.rickymorty.service.CharacterService;
import com.personagens.rickymorty.utils.CharacterAlias;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
public class CharacterServiceImpl implements CharacterService {

    private CharacterRepository characterRepository;

    private EpisodeRepository episodeRepository;

    private final String BASEURL = "https://rickandmortyapi.com/api";

    public CharacterServiceImpl(CharacterRepository characterRepository, EpisodeRepository episodeRepository) {
        this.characterRepository = characterRepository;
        this.episodeRepository = episodeRepository;
    }

    @Override
    public CharacterEntity saveCharacter(CharacterEntity characterEntity) {
        return characterRepository.save(characterEntity);
    }

    @Override
    public EpisodeEntity saveEpisode(EpisodeEntity episodeEntity) {
        return episodeRepository.save(episodeEntity);
    }

    @Override
    public List<CharacterClientResponseDTO> listAll() {
        Long startListAllExecution = System.currentTimeMillis();
        log.info("Starting process to list all character with episodes - Start: {}", startListAllExecution);
        List<CharacterEntity> findCharacter = characterRepository.findAll();

        if (!findCharacter.isEmpty()) {
            log.info("End of process: [{}] - Processing time: {}", System.currentTimeMillis(), System.currentTimeMillis() - startListAllExecution);
            return toCharacterClientResponseDTO(findCharacter);

        } else {
            CharactersApiResponse allCharacters = searchCharactersInRickyAndMortyApi();
            String url = BASEURL + "/episode";

            for (Results rt : allCharacters.getResults()) {
                RestTemplate restTemplate = new RestTemplate();
                searchEpisodesInRickAndMortyApi();
                List<EpisodeEntity> listEpisodes = new ArrayList<>();

                for (String internUrl : rt.getEpisode()) {
                    String urlEpisode = internUrl;
                    ResponseEntity<com.personagens.rickymorty.dto.episodes.Results> response = restTemplate.exchange(urlEpisode, HttpMethod.GET, null, com.personagens.rickymorty.dto.episodes.Results.class);
                    com.personagens.rickymorty.dto.episodes.Results episodeData = response.getBody();

                    EpisodeEntity episodeEntity = new EpisodeEntity();
                    episodeEntity.setName(episodeData.getName());
                    episodeEntity.setAirDate("Teste"/*episodeData.getAirDate()*/);
                    episodeEntity.setEpisode(episodeData.getEpisode());
                    episodeEntity.setUrl(episodeData.getUrl());
                    episodeEntity.setCreated(episodeData.getCreated());

                    listEpisodes.add(episodeEntity);
                    Long startSaveEpisodesExecution = System.currentTimeMillis();
                    log.info("Starting process to save the episode {} - Date and time: {}",
                            episodeEntity.getName(), startSaveEpisodesExecution);

                    if (episodeRepository.findById(episodeData.getId()).isEmpty()) {
                        saveEpisode(episodeEntity);
                    }

                    log.info("End of save episodes process: [{}] - Processing time: {}", System.currentTimeMillis(), System.currentTimeMillis() - startSaveEpisodesExecution);

                    CharacterEntity characterEntity = CharacterEntity.builder()
                            .name(rt.getName())
                            .status(rt.getStatus())
                            .url(rt.getUrl())
                            .created(rt.getCreated())
                            .build();

                    characterEntity.setEpisode(listEpisodes);

                    Long startSaveCharactersExecution = System.currentTimeMillis();
                    log.info("Starting process to save the episode {} - Date and time: {}",
                            episodeEntity.getName(), startSaveCharactersExecution);

                    saveCharacter(characterEntity);

                    log.info("End of save characters process: [{}] - Processing time: {}", System.currentTimeMillis(), System.currentTimeMillis() - startSaveCharactersExecution);

                }
            }

            log.info("End of list all process: [{}] - Processing time: {}", System.currentTimeMillis(), System.currentTimeMillis() - startListAllExecution);
            return toCharacterClientResponseDTO(characterRepository.findAll());
        }
    }

    @Override
    public CharacterClientResponseDTO searchByName(String nameToConvert) {
        if (nameToConvert.equals("-")) throw new ContentTypeException("Unknown name filter. Use a valid filter.");

        log.info("Character required: {} - Class: {}", nameToConvert, CharacterClientResponseDTO.class.getSimpleName());
        Long start = System.currentTimeMillis();
        log.info("Starting process to list all character with episodes - Start: {}", start);

        String name = CharacterAlias.aliasConversor(nameToConvert);
        List<CharacterClientResponseDTO> responseDTOList = listAll();

        Optional<CharacterClientResponseDTO> nameFind = responseDTOList.stream().filter(f -> f.getName().equals(name)).findAny();
        CharacterClientResponseDTO characterClientResponseDTO = nameFind.orElse(new CharacterClientResponseDTO());

        if (nameFind.isEmpty()) {
            log.error("Character not found!");
            throw new RuntimeException("Character not found.");
        }

        log.info("End of process: [{}] - Processing time: {}", System.currentTimeMillis(), System.currentTimeMillis() - start);
        return characterClientResponseDTO;
    }

    public CharactersApiResponse searchCharactersInRickyAndMortyApi() {
        String url = BASEURL + "/character";
        RestTemplate restTemplate = new RestTemplate();

        Long start = System.currentTimeMillis();
        log.info("External characters request for: {} - Start process: {}", url, start);

        ResponseEntity<CharactersApiResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, CharactersApiResponse.class);
        CharactersApiResponse charactersData = response.getBody();

        log.info("Characters response request: {} - End process: [{}] - Processing time: {}", response.getStatusCode(), System.currentTimeMillis(), System.currentTimeMillis() - start);
        return charactersData;
    }

    public EpisodesApiResponse searchEpisodesInRickAndMortyApi() {
        String url = BASEURL + "/episode";
        RestTemplate restTemplate = new RestTemplate();

        Long start = System.currentTimeMillis();
        log.info("External episodes request for: {} - Start process: {}", url, start);

        ResponseEntity<EpisodesApiResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, EpisodesApiResponse.class);
        EpisodesApiResponse episodesData = response.getBody();

        log.info("Episodes response request: {} - End process: [{}] - Processing time: {}", response.getStatusCode(), System.currentTimeMillis(), System.currentTimeMillis() - start);
        return episodesData;
    }

    //Mapper to response client.
    private List<CharacterClientResponseDTO> toCharacterClientResponseDTO(List<CharacterEntity> characterEntities) {

        List<EpisodeEntity> episodeEntities = episodeRepository.findAll();

        return characterEntities.stream()
                .map(character -> {
                    CharacterClientResponseDTO clientResponseDTO = new CharacterClientResponseDTO();
                    clientResponseDTO.setId(character.getId());
                    clientResponseDTO.setName(character.getName());
                    clientResponseDTO.setStatus(character.getStatus());
                    clientResponseDTO.setUrl(character.getUrl());
                    clientResponseDTO.setCreated(character.getCreated());
                    clientResponseDTO.setEpisode(episodeEntities);
                    return clientResponseDTO;
                })
                .collect(Collectors.toList());

    }
}
