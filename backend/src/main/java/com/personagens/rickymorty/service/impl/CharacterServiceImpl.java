package com.personagens.rickymorty.service.impl;

import com.personagens.rickymorty.dto.external.CharacterClientResponseDTO;
import com.personagens.rickymorty.dto.external.characters.CharactersApiResponse;
import com.personagens.rickymorty.dto.external.episodes.EpisodesApiResponse;
import com.personagens.rickymorty.repository.CharacterRepository;
import com.personagens.rickymorty.repository.EpisodeRepository;
import com.personagens.rickymorty.service.CharacterService;
import com.personagens.rickymorty.utils.CharacterAlias;
import com.personagens.rickymorty.entity.CharacterEntity;
import com.personagens.rickymorty.entity.EpisodeEntity;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class CharacterServiceImpl implements CharacterService {

    private CharacterRepository characterRepository;

    private EpisodeRepository episodeRepository;

    private ModelMapper modelMapper;

    private final String BASEURL = "https://rickandmortyapi.com/api";

    public CharacterServiceImpl(CharacterRepository characterRepository, EpisodeRepository episodeRepository,
                                ModelMapper modelMapper) {
        this.characterRepository = characterRepository;
        this.episodeRepository = episodeRepository;
        this.modelMapper = modelMapper;
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
        List<EpisodeEntity> findEpisode = episodeRepository.findAll();
        if (!findCharacter.isEmpty()) {
            for (CharacterEntity c : findCharacter) {
                c.setEpisode(findEpisode);
                saveCharacter(c);
            }
            log.info("End of process: [{}] - Processing time: {}", System.currentTimeMillis(), System.currentTimeMillis() - startListAllExecution);
            return toCharacterClientResponseDTO(findCharacter);

        } else {
            CharactersApiResponse allCharacters = searchCharactersInRickyAndMortyApi();
            EpisodesApiResponse allEpisodes = searchEpisodesInRickAndMortyApi();

            List<String> characterEpisodesList = new ArrayList<>();
            characterEpisodesList.addAll(allCharacters.getResults().get(0).getEpisode());//characterRepository.findAll().get(0).getEpisode());

            //Save episode
            List<EpisodeEntity> episodeEntity = allEpisodes.responseToEntity();
            Long startSaveEpisodesExecution = System.currentTimeMillis();
            log.info("Starting process to save the episode {} - Date and time: {}",
                    episodeEntity.get(0).getName(), startSaveEpisodesExecution);
            for (EpisodeEntity e : episodeEntity) {
                saveEpisode(e);
            }
            log.info("End of save episodes process: [{}] - Processing time: {}", System.currentTimeMillis(), System.currentTimeMillis() - startSaveEpisodesExecution);


            //Save character
            List<CharacterEntity> characterEntity = allCharacters.responseToEntity(allEpisodes.getResults().get(0)/*episodeEntity*/);
            Long startSaveCharactersExecution = System.currentTimeMillis();
            log.info("Starting process to save the episode {} - Date and time: {}",
                    episodeEntity.get(0).getName(), startSaveCharactersExecution);
            for (CharacterEntity c : characterEntity) {
                c.setEpisode(episodeEntity);
                saveCharacter(c);
            }
            log.info("End of save characters process: [{}] - Processing time: {}", System.currentTimeMillis(), System.currentTimeMillis() - startSaveCharactersExecution);

            log.info("End of list all process: [{}] - Processing time: {}", System.currentTimeMillis(), System.currentTimeMillis() - startListAllExecution);
            return toCharacterClientResponseDTO(characterRepository.findAll());
        }
    }

    @Override
    public CharacterClientResponseDTO searchByName(String nameToConvert) {
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

    //Mappers
    private CharacterEntity toCharacterEntity(Optional<CharacterEntity> characterEntity) {
        CharacterEntity entity = characterEntity.get();
        return entity;
    }

    private List<CharacterClientResponseDTO> toCharacterClientResponseDTO(List<CharacterEntity> characterEntities) {
        return characterEntities.stream()
                .map(entity -> modelMapper.map(entity, CharacterClientResponseDTO.class))
                .collect(Collectors.toList());
    }
}
