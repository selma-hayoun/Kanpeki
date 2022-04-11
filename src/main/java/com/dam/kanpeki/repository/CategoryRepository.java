package com.dam.kanpeki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam.kanpeki.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
