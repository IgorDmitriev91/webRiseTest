package com.example.webrisetest.service;

import com.example.webrisetest.dto.Subscription;
import com.example.webrisetest.dto.SuccessMessage;
import com.example.webrisetest.exception.NoValidData;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UserSubscriptionService {

    /**
     * Добавляет новую подписку для пользователя.
     *
     * @param id           уникальный идентификатор пользователя (UUID)
     * @param subscription объект подписки, содержащий информацию о сервисе
     * @return ResponseEntity с сообщением об успешном добавлении подписки
     */
    ResponseEntity<SuccessMessage> addSubscription(UUID id, Subscription subscription);

    /**
     * Удаляет подписку у пользователя.
     *
     * @param id    уникальный идентификатор пользователя (UUID)
     * @param subId уникальный идентификатор подписки (UUID)
     * @return ResponseEntity с сообщением об успешном удалении подписки
     * @throws NoValidData если подписка или пользователь не найдены
     */
    ResponseEntity<SuccessMessage> deleteSubscription(UUID id, UUID subId);

    /**
     * Получает список подписок пользователя.
     *
     * @param id уникальный идентификатор пользователя (UUID)
     * @return ResponseEntity со списком подписок пользователя или статусом NO_CONTENT, если подписок нет
     */

    ResponseEntity<List<Subscription>> getSubscription(UUID id);

    /**
     * Получает топ-3 подписок.
     *
     * @return ResponseEntity со списком топ-3 подписок
     */
    ResponseEntity<List<Subscription>> getTopSubscriptions();

}
