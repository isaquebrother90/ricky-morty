package com.personagens.rickymorty;

import com.personagens.rickymorty.entity.CharacterEntity;
import com.personagens.rickymorty.entity.EpisodeEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableSpringDataWebSupport
@EnableAutoConfiguration
@EntityScan(basePackageClasses = {CharacterEntity.class, EpisodeEntity.class})
@SpringBootApplication
public class RickyMortyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RickyMortyApplication.class, args);
	}

}
