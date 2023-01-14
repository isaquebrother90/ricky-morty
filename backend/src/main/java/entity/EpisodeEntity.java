package entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_episode")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EpisodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "air_date", nullable = false)
    private Date airDate;

    @Column(name = "episode", nullable = false, length = 50)
    private String episode;

    @Column(name = "url", nullable = false, length = 100)
    private String url;

    @Column(name = "created", nullable = false, length = 50)
    private String created;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private CharacterEntity character;
}
