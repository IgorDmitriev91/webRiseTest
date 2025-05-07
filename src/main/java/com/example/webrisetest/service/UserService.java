package com.example.webrisetest.service;

import com.example.webrisetest.dto.SuccessMessage;
import com.example.webrisetest.dto.UserRequestDTO;
import com.example.webrisetest.dto.UserResponseDTO;
import com.example.webrisetest.exception.UserNotFoundException;
import com.example.webrisetest.model.UserModel;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UserService {

    /**
     * Получает пользователя по его уникальному идентификатору.
     *
     * @param id уникальный идентификатор пользователя (UUID)
     * @return ResponseEntity с объектом UserResponseDTO, содержащим данные пользователя
     */
    ResponseEntity<UserResponseDTO> getUser(UUID id);

    /**
     * Сохраняет нового пользователя.
     *
     * @param userRequestDTO объект, содержащий данные нового пользователя
     * @return ResponseEntity с сообщением об успешном создании пользователя
     */
    ResponseEntity<SuccessMessage> saveUser(UserRequestDTO userRequestDTO);


    /**
     * Обновляет данные существующего пользователя.
     *
     * @param id             уникальный идентификатор пользователя (UUID)
     * @param userRequestDTO объект, содержащий обновленные данные пользователя
     * @return ResponseEntity с сообщением об успешном обновлении пользователя
     * @throws UserNotFoundException если пользователь с данным идентификатором не найден
     */
    ResponseEntity<SuccessMessage> changeUsers(UUID id, UserRequestDTO userRequestDTO);

    /**
     * Удаляет пользователя по его уникальному идентификатору.
     *
     * @param id уникальный идентификатор пользователя (UUID)
     * @return ResponseEntity с сообщением об успешном удалении пользователя
     * @throws UserNotFoundException если пользователь с данным идентификатором не найден
     */
    ResponseEntity<SuccessMessage> deleteUser(UUID id);

    /**
     * Находит пользователя по его уникальному идентификатору.
     *
     * @param id уникальный идентификатор пользователя (UUID)
     * @return объект UserModel, соответствующий найденному пользователю
     * @throws UserNotFoundException если пользователь с данным идентификатором не найден
     */
    UserModel findById(UUID id);
    /**
     * Проверяет пользователя по его уникальному идентификатору.
     *
     * @param id уникальный идентификатор пользователя (UUID)
     */
    void existsById(UUID id);

}
