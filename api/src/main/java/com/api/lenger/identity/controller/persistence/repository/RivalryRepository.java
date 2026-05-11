package com.api.lenger.identity.controller.persistence.repository;

import com.api.lenger.identity.controller.persistence.entity.Rivalry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RivalryRepository extends JpaRepository<Rivalry, UUID> {
    List<Rivalry> findByGroupIdAndStatus(UUID groupId, Rivalry.RivalryStatus status);
    List<Rivalry> findByStatus(Rivalry.RivalryStatus status);
}