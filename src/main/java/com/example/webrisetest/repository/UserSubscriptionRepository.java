package com.example.webrisetest.repository;

import com.example.webrisetest.model.SubscriptionModel;
import com.example.webrisetest.model.UserModel;
import com.example.webrisetest.model.UserSubscriptionModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscriptionModel, UUID> {
    Optional<UserSubscriptionModel> findByUserAndSubscription(UserModel user, SubscriptionModel subscription);

    List<UserSubscriptionModel> findByUserId(UUID userId);

    @Query(value = "SELECT us.subscription, COUNT(us) AS subscriptionCount " +
            "FROM UserSubscriptionModel us " +
            "GROUP BY us.subscription " +
            "ORDER BY subscriptionCount DESC")
    List<SubscriptionModel> findTopSubscriptions(Pageable pageable);
}
