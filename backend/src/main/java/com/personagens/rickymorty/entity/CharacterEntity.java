package com.personagens.rickymorty.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_character")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @JsonProperty("status")
    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @JsonProperty("url")
    @Column(name = "url", nullable = false, length = 100)
    private String url;

    @JsonProperty("created")
    @Column(name = "created", nullable = false, length = 50)
    private String created;

    @JsonProperty("Episodes")
    @OneToMany(mappedBy = "character")
    private List<EpisodeEntity> episode;
}
