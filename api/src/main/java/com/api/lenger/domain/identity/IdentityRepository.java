package com.api.lenger.domain.identity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IdentityRepository extends JpaRepository<Identity, UUID> {
    Optional<Identity> findByEmail(String email);
    Optional<Identity> findByProviderAndProviderId(Identity.AuthProvider provider, String providerId);
}

