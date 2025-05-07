package com.example.webrisetest.service;


import com.example.webrisetest.dto.SuccessMessage;
import com.example.webrisetest.dto.UserRequestDTO;
import com.example.webrisetest.dto.UserResponseDTO;
import com.example.webrisetest.exception.UserNotFoundException;
import com.example.webrisetest.mapper.UserMapper;
import com.example.webrisetest.model.UserModel;
import com.example.webrisetest.repository.UserRepository;
import com.example.webrisetest.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public ResponseEntity<UserResponseDTO> getUser(UUID id) {
        log.info("Получение пользователя с ID: {}", id);
        return ResponseEntity.ok().body(userMapper.toDTO(findById(id)));
    }

    @Override
    public ResponseEntity<SuccessMessage> saveUser(UserRequestDTO userRequestDTO) {
        UserModel userModel = userMapper.toEntity(userRequestDTO);
        UUID id = userRepository.save(userModel).getId();
        log.info("Добавлен новый пользователь с ID: {}", id);
        return ResponseEntity.created(URI.create(Utils.USER_URL + id))
                .body(new SuccessMessage(Utils.USER_SUCCESS_MESSAGE_CREATED));
    }

    @Override
    public ResponseEntity<SuccessMessage> changeUsers(UUID id, UserRequestDTO userRequestDTO) {
        log.info("Обновление пользователь с ID: {}", id);
        UserModel userModel = findById(id);
        Optional.ofNullable(userRequestDTO.getUserName())
                .ifPresent(userModel::setUserName);
        Optional.ofNullable(userRequestDTO.getEmail())
                .ifPresent(userModel::setEmail);
        userRepository.save(userModel);
        log.info("Пользователь с ID: {} обновлен", id);
        return ResponseEntity.ok().body(new SuccessMessage(Utils.USER_SUCCESS_MESSAGE_UPDATED));
    }

    @Override
    public ResponseEntity<SuccessMessage> deleteUser(UUID id) {
        log.info("Попытка удалить пользователя с ID: {}", id);
        existsById(id);
        userRepository.deleteById(id);
        log.info("Пользователь с ID: {} удален", id);
        return ResponseEntity.ok().body(new SuccessMessage(Utils.USER_SUCCESS_MESSAGE_DELETED));
    }

    @Override
    public UserModel findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Utils.USER_NOT_FOUND_WITH_ID));
    }

    public void existsById(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(Utils.USER_NOT_FOUND_WITH_ID);
        }
    }
}
