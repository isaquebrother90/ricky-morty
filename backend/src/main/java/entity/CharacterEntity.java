package entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_character")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "url", nullable = false, length = 100)
    private String url;

    @Column(name = "created", nullable = false, length = 50)
    private String created;

    @OneToMany(mappedBy = "character",  cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EpisodeEntity> episodes = new ArrayList<>();
}
