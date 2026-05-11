package com.api.lenger.identity.controller.persistence.repository;

import com.api.lenger.identity.controller.persistence.entity.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, UUID> {
    List<GroupMember> findByGroupIdOrderByPointsDesc(UUID groupId);
    Optional<GroupMember> findByGroupIdAndUserId(UUID groupId, UUID userId);
}
