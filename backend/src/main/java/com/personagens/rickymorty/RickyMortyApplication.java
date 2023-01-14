package com.personagens.rickymorty;

import entity.CharacterEntity;
import entity.EpisodeEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EnableAutoConfiguration
@EntityScan(basePackageClasses = {CharacterEntity.class, EpisodeEntity.class})
@SpringBootApplication
public class RickyMortyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RickyMortyApplication.class, args);
	}

}
