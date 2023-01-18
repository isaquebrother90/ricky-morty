package com.personagens.rickymorty.entity;

import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_character")
@Builder
public class CharacterEntity implements Serializable {
    private static final long serialVersionUID = 6027770984914382242L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "url", nullable = false, length = 100)
    private String url;

    @Column(name = "created", nullable = false, length = 50)
    private String created;

    @OneToMany(mappedBy = "character")
    private List<EpisodeEntity> episode;

    public CharacterEntity(Integer id, String name, String status, String url, String created, List<EpisodeEntity> episode) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.url = url;
        this.created = created;
        this.episode = episode;
    }

    public CharacterEntity() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<EpisodeEntity> getEpisode() {
        return episode;
    }

    public void setEpisode(List<EpisodeEntity> episode) {
        this.episode = episode;
    }

}
