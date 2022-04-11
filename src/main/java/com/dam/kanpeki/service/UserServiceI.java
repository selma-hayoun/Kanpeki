package com.dam.kanpeki.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.dam.kanpeki.model.User;

public interface UserServiceI {

	public List<User> findAllUsers();

	public List<User> findUsersOrderByDate();

	public List<User> findUsersCreatedAtBetweenDates(Date startDate, Date endDate);

	public List<User> findUsersBirthdayBetweenDates(Date startDate, Date endDate);

	public List<User> findByCity(String city);

	public List<User> findUsersByMatcher(String uField);

	public Optional<User> findById(Long id);

	public User addUser(User u);

	public void removeUserById(Long id);

	public void updateUser(User u);

}
