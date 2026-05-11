package com.api.lenger.rivalry;

import com.api.lenger.group.Group;
import com.api.lenger.rivalryParticipant.RivalryParticipant;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "rivalry")
@Getter @Setter @NoArgsConstructor
public class Rivalry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private RivalryType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private RivalryStatus status = RivalryStatus.ACTIVE;

    @OneToMany(mappedBy = "rivalry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RivalryParticipant> participants = new ArrayList<>();

    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt = LocalDateTime.now();

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum RivalryType { DUEL, TEAM, GROUP_RANKING }
    public enum RivalryStatus { ACTIVE, FINISHED, CANCELLED }
}
