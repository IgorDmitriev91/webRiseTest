package com.example.webrisetest.service;

import com.example.webrisetest.dto.Subscription;
import com.example.webrisetest.exception.SubscriptionNotFoundException;
import com.example.webrisetest.model.SubscriptionModel;

import java.util.UUID;

public interface SubscriptionService {

    /**
     * Находит подписку по ее имени.
     *
     * @param subscription объект типа Subscription, содержащий имя сервиса подписки
     * @return объект SubscriptionModel, соответствующий найденной подписке
     * @throws SubscriptionNotFoundException если подписка с данным именем не найдена
     */
    SubscriptionModel findByName(Subscription subscription);


    /**
     * Находит подписку по ее уникальному идентификатору.
     *
     * @param id уникальный идентификатор подписки (UUID)
     * @return объект SubscriptionModel, соответствующий найденной подписке
     * @throws SubscriptionNotFoundException если подписка с данным идентификатором не найдена
     */
    SubscriptionModel findById(UUID id);

}
