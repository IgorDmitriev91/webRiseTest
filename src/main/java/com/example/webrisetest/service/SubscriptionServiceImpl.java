package com.example.webrisetest.service;


import com.example.webrisetest.dto.Subscription;
import com.example.webrisetest.exception.SubscriptionNotFoundException;
import com.example.webrisetest.model.SubscriptionModel;
import com.example.webrisetest.repository.SubscriptionRepository;
import com.example.webrisetest.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public SubscriptionModel findByName(Subscription subscription) {
        return subscriptionRepository.findByServiceName(subscription.getServiceName())
                .orElseThrow(() -> new SubscriptionNotFoundException(Utils.SUBSCRIPTION_WITH_THIS_NAME_NOT_FOUND));
    }

    @Override
    public SubscriptionModel findById(UUID id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new SubscriptionNotFoundException(Utils.SUBSCRIPTION_NOT_FOUND_WITH_ID));
    }
}
