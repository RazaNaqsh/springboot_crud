package com.example.spring_crud.Repo;

import com.example.spring_crud.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
