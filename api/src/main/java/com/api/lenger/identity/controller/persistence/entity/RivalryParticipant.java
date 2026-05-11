package com.api.lenger.identity.controller.persistence.entity;
@Entity
@Table(name = "rivalry_participant",
        uniqueConstraints = @UniqueConstraint(columnNames = {"rivalry_id", "user_id"}))
@Getter @Setter @NoArgsConstructor
public class RivalryParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rivalry_id", nullable = false)
    private Rivalry rivalry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "team_name", length = 100)
    private String teamName;

    @Column(nullable = false)
    private int score = 0;

    private Integer rank;
}