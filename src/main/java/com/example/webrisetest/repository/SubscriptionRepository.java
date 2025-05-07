package com.example.webrisetest.repository;

import com.example.webrisetest.model.SubscriptionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<SubscriptionModel, UUID> {
    Optional<SubscriptionModel> findByServiceName(String name);
}
