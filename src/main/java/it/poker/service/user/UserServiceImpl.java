package it.poker.service.user;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.poker.model.User;
import it.poker.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public User findByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	@Transactional
	public void insert(User user) {
		userRepository.save(user);
	}

	@Override
	@Transactional
	public List<User> findUserByTavolo(Long idTavolo) {
		return userRepository.findUserByTavolo(idTavolo);
	}

	@Override
	@Transactional
	public User findById(Long idUser) {
		return userRepository.findById(idUser).orElse(null);
	}
	
}
