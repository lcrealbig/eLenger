package com.api.lenger.identity.controller.persistence.entity;

import java.util.UUID;

@Entity
@Table(name = "app_user")
@Getter @Setter @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identity_id", nullable = false, unique = true)
    private Identity identity;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "total_points", nullable = false)
    private int totalPoints = 0;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();
}