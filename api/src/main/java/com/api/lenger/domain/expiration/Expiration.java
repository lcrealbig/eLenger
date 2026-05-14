package com.api.lenger.domain.expiration;

import com.api.lenger.domain.expiration.type.OwnerType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "expiration")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Expiration {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "owner_type", nullable = false)
    private OwnerType owner;

    @Column(name = "owner_id", nullable = false)
    private String ownerId;

    @Column(name = "expiration_in_seconds", nullable = false)
    private Long expirationInSeconds;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "expiration_at")
    private LocalDateTime expirationAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        this.expirationAt = now.plusSeconds(expirationInSeconds);
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.expirationAt = this.updatedAt.plusSeconds(expirationInSeconds);
    }
}