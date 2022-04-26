package com.dam.kanpeki.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	Optional<User> findByEmail(String email);

	Optional<User> findByNickname(String nickname);

	@Query(value = "SELECT COUNT(*) FROM users u WHERE u.nickname = :nickname", nativeQuery = true)
	Integer countUserNicknameUnique(@Param("nickname") String nickname);

}
