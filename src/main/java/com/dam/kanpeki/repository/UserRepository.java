package com.dam.kanpeki.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dam.kanpeki.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM users u ORDER BY u.created_at DESC", nativeQuery = true)
	List<User> findUsersOrderByDate();

	@Query(value = "SELECT * FROM users u WHERE u.created_at BETWEEN :startDate AND :endDate ORDER BY u.created_at DESC", nativeQuery = true)
	List<User> findUsersCreatedAtBetweenDates(Date startDate, Date endDate);

	@Query(value = "SELECT * FROM users u WHERE u.birthday BETWEEN :startDate AND :endDate ORDER BY u.birthday DESC", nativeQuery = true)
	List<User> findUsersBirthdayBetweenDates(Date startDate, Date endDate);

	List<User> findByCity(String city);

}
