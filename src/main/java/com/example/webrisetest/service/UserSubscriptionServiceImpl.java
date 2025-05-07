package com.example.webrisetest.service;

import com.example.webrisetest.dto.Subscription;
import com.example.webrisetest.dto.SuccessMessage;
import com.example.webrisetest.exception.NoValidData;
import com.example.webrisetest.mapper.SubscriptionMapper;
import com.example.webrisetest.model.SubscriptionModel;
import com.example.webrisetest.model.UserModel;
import com.example.webrisetest.model.UserSubscriptionModel;
import com.example.webrisetest.repository.UserSubscriptionRepository;
import com.example.webrisetest.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSubscriptionServiceImpl implements UserSubscriptionService {
    private final UserSubscriptionRepository userSubscriptionRepository;

    private final UserServiceImpl userService;

    private final SubscriptionServiceImpl subscriptionService;

    private final SubscriptionMapper subscriptionMapper;

    @Override
    public ResponseEntity<SuccessMessage> addSubscription(UUID id, Subscription subscription) {
        log.info("Попытка добавления подписки {}, для пользователя c id {}", subscription.getServiceName(), id);
        UserModel userModel = userService.findById(id);
        SubscriptionModel subscriptionModel = subscriptionService.findByName(subscription);
        userSubscriptionRepository.save(
                UserSubscriptionModel.builder()
                        .user(userModel)
                        .subscription(subscriptionModel)
                        .build());
        log.info("Для пользователя c id {}, подписка {} успешно добавлена", id, subscription.getServiceName());
        return ResponseEntity.ok().body(new SuccessMessage(Utils.SUBSCRIPTION_SUCCESS_MESSAGE_ADDED));
    }

    @Override
    public ResponseEntity<SuccessMessage> deleteSubscription(UUID id, UUID subId) {
        UserModel userModel = userService.findById(id);
        SubscriptionModel subscriptionModel = subscriptionService.findById(subId);
        log.info("Попытка удалить подписку {}, у пользователя с id {}", subscriptionModel.getServiceName(), id);

        UserSubscriptionModel userSubscription = userSubscriptionRepository
                .findByUserAndSubscription(userModel, subscriptionModel)
                .orElseThrow(() -> new NoValidData(Utils.SUBSCRIPTION_OR_USER_NOT_VALID));
        userSubscriptionRepository.delete(userSubscription);
        log.info("Удаление подписки {}, у пользователя с id {}", subscriptionModel.getServiceName(), id);
        return ResponseEntity.ok(new SuccessMessage(Utils.SUBSCRIPTION_SUCCESS_MESSAGE_DELETED));
    }

    @Override
    public ResponseEntity<List<Subscription>> getSubscription(UUID id) {
        log.info("Получение  подписок  пользователя с id {}", id);
        userService.existsById(id);
        List<UserSubscriptionModel> userSubscriptions = userSubscriptionRepository.findByUserId(id);
        List<Subscription> subscriptions = userSubscriptions
                .stream()
                .map(UserSubscriptionModel::getSubscription)
                .map(subscriptionMapper::toDTO)
                .toList();
        HttpStatus httpStatus = subscriptions.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(httpStatus).body(subscriptions);
    }

    @Override
    public ResponseEntity<List<Subscription>> getTopSubscriptions() {
        log.info("Получение топа подписок");
        return ResponseEntity.ok().body(userSubscriptionRepository
                .findTopSubscriptions(PageRequest.of(0, 3))
                .stream()
                .map(subscriptionMapper::toDTO)
                .toList());
    }
}
