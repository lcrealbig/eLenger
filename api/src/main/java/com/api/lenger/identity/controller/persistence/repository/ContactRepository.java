package com.api.lenger.identity.controller.persistence.repository;

import com.api.lenger.identity.controller.persistence.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID> {
    List<Contact> findByOwnerIdAndStatus(UUID ownerId, Contact status);
    Optional<Contact> findByOwnerIdAndContactId(UUID ownerId, UUID contactId);
}
