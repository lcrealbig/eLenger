package com.api.lenger.rivalryParticipant;

import com.api.lenger.rivalry.Rivalry;
import com.api.lenger.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "rivalry_participant",
        uniqueConstraints = @UniqueConstraint(columnNames = {"rivalry_id", "user_id"}))
@Getter
@Setter
@NoArgsConstructor
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