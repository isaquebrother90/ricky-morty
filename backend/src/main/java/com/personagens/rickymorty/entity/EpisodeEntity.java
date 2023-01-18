package com.personagens.rickymorty.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_episode")
@Builder
public class EpisodeEntity implements Serializable {
    private static final long serialVersionUID = 6027770984914382242L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @JsonProperty("airDate")
    @Column(name = "air_date", length = 50)
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
    @ManyToOne()
    @JoinColumn(name = "tb_episode_id")
    private CharacterEntity character;

    public EpisodeEntity() {
    }

    public EpisodeEntity(Integer id, String name, String airDate, String episode, String url, String created, CharacterEntity character) {
        this.id = id;
        this.name = name;
        this.airDate = airDate;
        this.episode = episode;
        this.url = url;
        this.created = created;
        this.character = character;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public CharacterEntity getCharacter() {
        return character;
    }

    public void setCharacter(CharacterEntity character) {
        this.character = character;
    }
}
