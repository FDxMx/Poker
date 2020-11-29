package it.poker.service.tavolo;

import java.util.List;

import it.poker.model.Tavolo;

public interface TavoloService {
	
	public void insert(Tavolo tavolo);
	
	public void update(Tavolo tavolo);
	
	public void delete(Tavolo tavolo);
	
	public Tavolo findById(long idTavolo);
	
	public List<Tavolo> findTavoliByCreatore(Long idUser);
	
	public List<Tavolo> findTavoloByExample(Tavolo tavolo);

}
