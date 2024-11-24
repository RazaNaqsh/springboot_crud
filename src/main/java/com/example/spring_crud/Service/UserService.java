package com.example.spring_crud.Service;


import com.example.spring_crud.DTO.UserInputDTO;
import com.example.spring_crud.DTO.UserOutputDTO;
import com.example.spring_crud.Model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<UserOutputDTO> addUser(UserInputDTO userInputDTO);

    List<UserOutputDTO> getAllUsers();

    ResponseEntity<UserOutputDTO> getUserById(Integer id);

    String deleteUser(Integer id);

    ResponseEntity<UserOutputDTO> updateUser(UserInputDTO userInputDTO, Integer id);
}
