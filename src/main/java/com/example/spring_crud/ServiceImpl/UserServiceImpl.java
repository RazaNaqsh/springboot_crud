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
import java.util.Optional;
import java.util.OptionalInt;
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
        responseDTO.setId(savedUser.getId());
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
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setAge(user.getAge());
            dto.setEmail(user.getEmail());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<UserOutputDTO> getUserById(Integer id) {
        Optional<User> user = userRepo.findById(id);

        if(user.isPresent()){
            User foundUser = user.get();
            UserOutputDTO response = new UserOutputDTO();
            response.setId(foundUser.getId());
            response.setName(foundUser.getName());
            response.setAge(foundUser.getAge());
            response.setEmail(foundUser.getEmail());

            return ResponseEntity.ok(response);
        }
        return null;
    }

    @Override
    public String deleteUser(Integer id) {
        Optional<User> deletedUser = userRepo.findById(id);
        if(deletedUser.isPresent()){
            User user = deletedUser.get();
            userRepo.deleteById(id);
            return user.getName().toUpperCase() + " has been deleted";
        }else{
        return "User Not Found";
        }

    }

    @Override
    public ResponseEntity<UserOutputDTO> updateUser(UserInputDTO userInputDTO, Integer id) {
        Optional<User> existingUser = userRepo.findById(id);

        if(existingUser.isPresent()){
            User FoundUser = existingUser.get();
            FoundUser.setName(userInputDTO.getName());
            FoundUser.setAge(userInputDTO.getAge());
            FoundUser.setEmail(userInputDTO.getEmail());
            FoundUser.setPassword(userInputDTO.getPassword());

            UserOutputDTO response = new UserOutputDTO();
            response.setId(FoundUser.getId());
            response.setName(FoundUser.getName());
            response.setAge(FoundUser.getAge());
            response.setEmail(FoundUser.getEmail());

            return ResponseEntity.ok(response);
        }
        return null;
    }
}
