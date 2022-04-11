package com.dam.kanpeki.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.dam.kanpeki.model.User;
import com.dam.kanpeki.repository.UserRepository;
import com.dam.kanpeki.service.UserServiceI;

@Service
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private UserRepository uRepo;

	@Override
	public List<User> findAllUsers() {
		return uRepo.findAll();
	}

	@Override
	public List<User> findUsersOrderByDate() {
		return uRepo.findUsersOrderByDate();
	}

	@Override
	public List<User> findUsersCreatedAtBetweenDates(Date startDate, Date endDate) {
		return uRepo.findUsersCreatedAtBetweenDates(startDate, endDate);
	}

	@Override
	public List<User> findUsersBirthdayBetweenDates(Date startDate, Date endDate) {
		return uRepo.findUsersBirthdayBetweenDates(startDate, endDate);
	}

	@Override
	public List<User> findByCity(String city) {
		return uRepo.findByCity(city);
	}

	@Override
	public List<User> findUsersByMatcher(String uField) {
		User u = new User();
		u.setEmail(uField);
		u.setFullName(uField);
		u.setNickname(uField);

		ExampleMatcher customExMatcher = ExampleMatcher.matchingAny()
				.withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("fullName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("nickname", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

		Example<User> userExample = Example.of(u, customExMatcher);

		return uRepo.findAll(userExample);
	}

	@Override
	public Optional<User> findById(Long id) {
		return uRepo.findById(id);
	}

	@Override
	public User addUser(User u) {
		return uRepo.save(u);
	}

	@Override
	public void removeUserById(Long id) {
		uRepo.deleteById(id);
	}

	@Override
	public void updateUser(User u) {
		uRepo.save(u);
	}

}
