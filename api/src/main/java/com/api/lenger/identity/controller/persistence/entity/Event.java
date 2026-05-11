package com.api.lenger.identity.controller.persistence.entity;

@Entity
@Table(name = "event")
@Getter @Setter @NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    private String description;
    private String location;

    @Column(name = "event_date")
    private LocalDateTime eventDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "is_public", nullable = false)
    private boolean isPublic = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EventStatus status = EventStatus.UPCOMING;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventParticipant> participants = new ArrayList<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    public enum EventStatus { UPCOMING, ACTIVE, FINISHED, CANCELLED }
}
