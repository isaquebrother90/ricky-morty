package com.personagens.rickymorty.controller;

import com.personagens.rickymorty.dto.external.CharacterClientResponseDTO;
import com.personagens.rickymorty.service.impl.CharacterServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Rick and Morty API")
@RestController
@RequestMapping("/api/v1/characters")
public class RickyMortyController {
    private CharacterServiceImpl characterServiceImpl;

    public RickyMortyController(CharacterServiceImpl characterServiceImpl) {
        this.characterServiceImpl = characterServiceImpl;
    }

    @ApiOperation(value = "Get all characters")
    @GetMapping
    public ResponseEntity<List<CharacterClientResponseDTO>> listAll() {
        return ResponseEntity.ok(characterServiceImpl.listAll());
    }

    @ApiOperation(value = "Get characters by name")
    @GetMapping("/{name}")
    public ResponseEntity<CharacterClientResponseDTO> searchByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(characterServiceImpl.searchByName(name));
    }
}
