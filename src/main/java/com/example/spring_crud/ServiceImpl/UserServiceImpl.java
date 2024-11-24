package com.example.spring_crud.ServiceImpl;

import com.example.spring_crud.DTO.UserInputDTO;
import com.example.spring_crud.DTO.UserOutputDTO;
import com.example.spring_crud.Model.User;
import com.example.spring_crud.Repo.UserRepo;
import com.example.spring_crud.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public ResponseEntity<UserOutputDTO> addUser(UserInputDTO userInputDTO) {
        //maps userInputDTO to user entity;
        User newUser = new User();
        newUser.setName(userInputDTO.getName());
        newUser.setAge(userInputDTO.getAge());
        newUser.setEmail(userInputDTO.getEmail());
        newUser.setPassword(userInputDTO.getPassword());

        User savedUser = userRepo.save(newUser);

        //maps created User entity to userOutputDTO
        UserOutputDTO responseDTO = new UserOutputDTO();
        responseDTO.setName(savedUser.getName());
        responseDTO.setAge(savedUser.getAge());
        responseDTO.setEmail(savedUser.getEmail());


        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Override
    public List<UserOutputDTO> getAllUsers() {
        List<User> users = userRepo.findAll();

        return users.stream().map(user -> {
            UserOutputDTO dto = new UserOutputDTO();
            dto.setName(user.getName());
            dto.setAge(user.getAge());
            dto.setEmail(user.getEmail());
            return dto;
        }).collect(Collectors.toList());
    }
}
