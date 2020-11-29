package it.poker.service.user;

import java.util.List;

import it.poker.model.User;

public interface UserService {
	
	public void insert(User user);
	
	public User findById(Long idUser);
	
	public User findByUsernameAndPassword(String username, String password);
	
	public List<User> findUserByTavolo(Long idTavolo);
	
}
