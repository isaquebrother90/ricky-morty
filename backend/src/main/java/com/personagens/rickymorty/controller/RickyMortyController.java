package com.personagens.rickymorty.controller;

import com.personagens.rickymorty.dto.CharacterClientResponseDTO;
import com.personagens.rickymorty.service.impl.CharacterServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Api(value = "Rick and Morty API")
@RestController
@RequestMapping("/api/v1/characters")
public class RickyMortyController {
    private CharacterServiceImpl characterServiceImpl;

    public RickyMortyController(CharacterServiceImpl characterServiceImpl) {
        this.characterServiceImpl = characterServiceImpl;
    }

    @Cacheable("characters")
    @ApiOperation(value = "Get all characters")
    @GetMapping
    public ResponseEntity<List<CharacterClientResponseDTO>> listAllCharacter() {
        return ResponseEntity.ok(characterServiceImpl.listAll());
    }

    @Cacheable("character")
    @ApiOperation(value = "Get characters by name")
    @GetMapping("/{name}")
    public ResponseEntity<CharacterClientResponseDTO> searchByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(characterServiceImpl.searchByName(name));
    }

    /*@GetMapping("/pageable")
    ResponseEntity<Page<CharacterClientResponseDTO>> index(CharacterClientResponseDTO filter, @PageableDefault Pageable pageable) {
        Page<CharacterClientResponseDTO> entities = characterServiceImpl.getAllPageable(filter, pageable);
        return ResponseEntity.ok(entities);
    }*/
}
