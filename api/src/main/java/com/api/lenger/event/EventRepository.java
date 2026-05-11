package com.api.lenger.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    List<Event> findByOwnerIdOrderByEventDateDesc(UUID ownerId);
    List<Event> findByGroupIdAndStatus(UUID groupId, Event.EventStatus status);
    List<Event> findByIsPublicTrueAndStatus(Event.EventStatus status);
}
