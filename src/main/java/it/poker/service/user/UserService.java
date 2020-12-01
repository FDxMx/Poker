package it.poker.service.user;

import java.util.List;

import it.poker.model.User;

public interface UserService {
	
	public void insert(User user);
	
	public void update(User user);
	
	public List<User> findAll();
	
	public User findById(long idUser);
	
	public User findByUsernameAndPassword(String username, String password);
	
	public List<User> findUserByTavolo(long idTavolo);
	
	public User findUserWithRuoli(long idUser);
	
}
