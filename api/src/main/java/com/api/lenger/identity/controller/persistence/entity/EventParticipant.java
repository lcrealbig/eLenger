package com.api.lenger.identity.controller.persistence.entity;

@Entity
@Table(name = "event_participant",
        uniqueConstraints = @UniqueConstraint(columnNames = {"event_id", "user_id"}))
@Getter @Setter @NoArgsConstructor
public class EventParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Integer score;
    private Integer rank;

    @Column(name = "joined_at", nullable = false, updatable = false)
    private LocalDateTime joinedAt = LocalDateTime.now();
}
