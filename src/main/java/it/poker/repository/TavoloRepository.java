package it.poker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.poker.model.Tavolo;

public interface TavoloRepository extends CrudRepository<Tavolo, Long>,QueryByExampleExecutor <Tavolo>{
	
	@Query("FROM Tavolo t JOIN t.creatoreTavolo u WHERE u.id = ?1")
	List<Tavolo> findTavoliByCreatore(Long idUser);
	
}
