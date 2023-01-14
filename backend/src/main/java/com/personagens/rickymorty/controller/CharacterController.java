package com.personagens.rickymorty.controller;

import com.personagens.rickymorty.dto.CharacterResponseDTO;
import com.personagens.rickymorty.service.impl.CharacterServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/characters")
public class CharacterController {
    private CharacterServiceImpl characterServiceImpl;

    public CharacterController(CharacterServiceImpl characterServiceImpl) {
        this.characterServiceImpl = characterServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<CharacterResponseDTO>> listAll(){
        return ResponseEntity.ok(characterServiceImpl.listAll());
    }

    //Quando quiser filtrar por algum nome, basta colocar barra e o nome.
    @GetMapping("/{name}")
    public ResponseEntity<CharacterResponseDTO> searchByName(@PathVariable("name") String name){
        return ResponseEntity.ok(characterServiceImpl.searchByName(name));
    }
}
