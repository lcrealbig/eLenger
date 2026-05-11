package com.api.lenger.identity.controller.persistence.entity;

@Entity
@Table(name = "group_member",
        uniqueConstraints = @UniqueConstraint(columnNames = {"group_id", "user_id"}))
@Getter @Setter @NoArgsConstructor
public class GroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private GroupRole role = GroupRole.MEMBER;

    @Column(nullable = false)
    private int points = 0;

    @Column(name = "joined_at", nullable = false, updatable = false)
    private LocalDateTime joinedAt = LocalDateTime.now();

    public enum GroupRole { OWNER, ADMIN, MEMBER }
}
