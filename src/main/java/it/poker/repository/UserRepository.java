package it.poker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.poker.model.User;

public interface UserRepository extends CrudRepository<User, Long>,QueryByExampleExecutor <User>{
	
	User findByUsernameAndPassword(String username, String password);
	
	@Query("FROM User u JOIN u.tavolo t WHERE t.id = ?1")
	List<User> findUserByTavolo(Long idTavolo);

}
