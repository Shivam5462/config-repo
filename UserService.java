package com.coforge.finance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coforge.finance.model.User;
import com.coforge.finance.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public Optional<User> findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public Optional<User> findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public List<User> findActiveUsers() {
		return userRepository.findByIsActive(true);
	}

	public User deactivateUser(Long userId) {
		Optional<User> userOpt = userRepository.findById(userId);
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			user.setIsActive(false);  
			return userRepository.save(user);  
		}
		return null;
	}

	public User activateUser(Long userId) {
		Optional<User> userOpt = userRepository.findById(userId);
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			user.setIsActive(true);  
			return userRepository.save(user);  
		}
		return null;
	}

	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}