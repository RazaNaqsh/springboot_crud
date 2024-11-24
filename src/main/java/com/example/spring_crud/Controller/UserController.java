package com.example.spring_crud.Controller;


import com.example.spring_crud.DTO.UserInputDTO;
import com.example.spring_crud.DTO.UserOutputDTO;
import com.example.spring_crud.Model.User;
import com.example.spring_crud.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserOutputDTO> addUser(@RequestBody UserInputDTO userInputDTO){
        return userService.addUser(userInputDTO);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserOutputDTO>> getALlUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserOutputDTO> getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Integer id){
        return userService.deleteUser(id);
    }
}
