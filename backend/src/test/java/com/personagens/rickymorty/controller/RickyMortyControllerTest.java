package com.personagens.rickymorty.controller;

import com.personagens.rickymorty.dto.CharacterClientResponseDTO;
import com.personagens.rickymorty.service.impl.CharacterServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RickyMortyControllerTest {

    @Mock
    private CharacterServiceImpl characterServiceImpl;

    @InjectMocks
    private RickyMortyController rickyMortyController;

    @Before
    public void setUp() {
        List<CharacterClientResponseDTO> characters = new ArrayList<>();
        characters.add(new CharacterClientResponseDTO());
        characters.add(new CharacterClientResponseDTO());
        when(characterServiceImpl.listAll()).thenReturn(characters);
        when(characterServiceImpl.searchByName("Rick Sanchez")).thenReturn(new CharacterClientResponseDTO());
    }

    @Test
    public void listAllCharacter_ShouldCallListAllMethodOfCharacterServiceImpl() {
        rickyMortyController.listAllCharacter();
        verify(characterServiceImpl).listAll();
    }

    @Test
    public void listAllCharacter_ShouldReturnListOfCharacters() {
        List<CharacterClientResponseDTO> expectedCharacters = new ArrayList<>();
        expectedCharacters.add(new CharacterClientResponseDTO());
        expectedCharacters.add(new CharacterClientResponseDTO());

        ResponseEntity<List<CharacterClientResponseDTO>> response = rickyMortyController.listAllCharacter();

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(expectedCharacters, response.getBody());
        verify(characterServiceImpl, times(1)).listAll();
    }

    @Test
    public void searchByName_ShouldCallSearchByNameMethodOfCharacterServiceImpl() {
        rickyMortyController.searchByName("Rick Sanchez");
        verify(characterServiceImpl).searchByName("Rick Sanchez");
    }

    @Test
    public void searchByName_ShouldReturnCharacterByName() {
        String expectedName = "Rick Sanchez";
        CharacterClientResponseDTO expectedCharacter = new CharacterClientResponseDTO();
        when(characterServiceImpl.searchByName(expectedName)).thenReturn(expectedCharacter);

        ResponseEntity<CharacterClientResponseDTO> response = rickyMortyController.searchByName(expectedName);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(expectedCharacter, response.getBody());
        verify(characterServiceImpl, times(1)).searchByName(expectedName);
    }
}
