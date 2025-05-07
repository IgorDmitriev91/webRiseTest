package com.example.webrisetest.controller;


import com.example.webrisetest.dto.Subscription;
import com.example.webrisetest.dto.SuccessMessage;
import com.example.webrisetest.service.UserSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class UserSubscriptionController implements SubscriptionApi {

    private final UserSubscriptionService userSubscriptionService;

    @Override
    public ResponseEntity<SuccessMessage> addSubscription(UUID id, Subscription subscription) {
        return userSubscriptionService.addSubscription(id, subscription);
    }

    @Override
    public ResponseEntity<SuccessMessage> deleteSubscription(UUID id, UUID subId) {
        return userSubscriptionService.deleteSubscription(id, subId);
    }

    @Override
    public ResponseEntity<List<Subscription>> getSubscription(UUID id) {
        return userSubscriptionService.getSubscription(id);
    }

    @Override
    public ResponseEntity<List<Subscription>> getTopSubscriptions() {
        return userSubscriptionService.getTopSubscriptions();
    }
}
