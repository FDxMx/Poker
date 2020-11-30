package it.poker.service.tavolo;

import java.util.List;

import it.poker.model.Tavolo;
import it.poker.model.User;

public interface TavoloService {
	
	public void insert(Tavolo tavolo);
	
	public void update(Tavolo tavolo);
	
	public void delete(Tavolo tavolo);
	
	public List<Tavolo> findAll();
	
	public Tavolo findById(long idTavolo);
	
	public List<Tavolo> findTavoliByCreatore(Long idUser);
	
	public List<Tavolo> findTavoloByExample(Tavolo tavolo, User user);
	
	public List<Tavolo> findTavoloByExampleWithUser(Tavolo tavolo, User user, User userSession);

}
