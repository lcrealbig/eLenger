package com.api.lenger.identity.controller.persistence.repository;

import com.api.lenger.identity.controller.persistence.entity.RivalryParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RivalryParticipantRepository extends JpaRepository<RivalryParticipant, UUID> {
    List<RivalryParticipant> findByRivalryIdOrderByScoreDesc(UUID rivalryId);
    List<RivalryParticipant> findByUserId(UUID userId);
}