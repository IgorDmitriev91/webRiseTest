package com.example.webrisetest.controller;

import com.example.webrisetest.dto.SuccessMessage;
import com.example.webrisetest.dto.UserRequestDTO;
import com.example.webrisetest.dto.UserResponseDTO;
import com.example.webrisetest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController implements UsersApi {

    private final UserService userService;


    @Override
    public ResponseEntity<UserResponseDTO> getUser(UUID id) {
        return userService.getUser(id);
    }

    @Override
    public ResponseEntity<SuccessMessage> saveUser(UserRequestDTO userRequestDTO) {
        return userService.saveUser(userRequestDTO);
    }

    @Override
    public ResponseEntity<SuccessMessage> putUsers(UUID id, UserRequestDTO userRequestDTO) {
        return userService.changeUsers(id, userRequestDTO);
    }

    @Override
    public ResponseEntity<SuccessMessage> deleteUser(UUID id) {
        return userService.deleteUser(id);
    }

}
