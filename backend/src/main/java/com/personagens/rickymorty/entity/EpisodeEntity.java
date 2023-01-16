package com.personagens.rickymorty.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_episode")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EpisodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @JsonProperty("air_date")
    @Column(name = "air_date", nullable = false)
    private String airDate;

    @JsonProperty("episode")
    @Column(name = "episode", nullable = false, length = 50)
    private String episode;

    @JsonProperty("url")
    @Column(name = "url", nullable = false, length = 100)
    private String url;

    @JsonProperty("created")
    @Column(name = "created", nullable = false, length = 50)
    private String created;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private CharacterEntity character;
}
