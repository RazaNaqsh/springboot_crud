package com.example.spring_crud.DTO;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInputDTO {
    private String name;
    private Integer age;
    private String email;
    private String password;
}