package com.dam.kanpeki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam.kanpeki.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
