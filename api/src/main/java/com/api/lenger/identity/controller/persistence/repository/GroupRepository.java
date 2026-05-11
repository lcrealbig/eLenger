package com.api.lenger.identity.controller.persistence.repository;

import com.api.lenger.identity.controller.persistence.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<Group, UUID> {
    List<Group> findByIsPublicTrue();
    List<Group> findByOwnerId(UUID ownerId);
}

