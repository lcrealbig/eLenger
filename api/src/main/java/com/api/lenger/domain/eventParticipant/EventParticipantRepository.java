package com.api.lenger.domain.eventParticipant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventParticipantRepository extends JpaRepository<EventParticipant, UUID> {
    List<EventParticipant> findByEventIdOrderByRankAsc(UUID eventId);
    List<EventParticipant> findByUserId(UUID userId);
}

