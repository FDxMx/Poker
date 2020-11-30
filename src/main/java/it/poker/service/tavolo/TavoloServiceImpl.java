package it.poker.service.tavolo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List; 

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.poker.model.Tavolo;
import it.poker.model.User;
import it.poker.repository.TavoloRepository;

@Service
public class TavoloServiceImpl implements TavoloService {
	
	@Autowired
	private TavoloRepository tavoloRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public List<Tavolo> findTavoliByCreatore(Long idUser) {
		return tavoloRepository.findTavoliByCreatore(idUser);
	}

	@Override
	@Transactional
	public void insert(Tavolo tavolo) {
		tavoloRepository.save(tavolo);
	}

	@Override
	@Transactional
	public Tavolo findById(long idTavolo) {
		return tavoloRepository.findById(idTavolo).orElse(null);
	}

	@Override
	@Transactional
	public void update(Tavolo tavolo) {
		tavoloRepository.save(tavolo);
		
	}

	@Override
	@Transactional
	public void delete(Tavolo tavolo) {
		tavoloRepository.delete(tavolo);
	}
	
	@Override
	@Transactional
	public List<Tavolo> findTavoloByExample(Tavolo tavolo, User user) {
		String query = "select t from Tavolo t join t.creatoreTavolo u where 1=1 ";
		if (user != null) {
			query += " and u.id = " + user.getId();
		}
		if (tavolo.getEsperienzaMinima() > 0) {
			query += " and t.esperienzaMinima = " + tavolo.getEsperienzaMinima();
		}
		if (tavolo.getCreditoMinimo() > 0) {
			query += " and t.creditoMinimo = " + tavolo.getCreditoMinimo();
		}
		if (StringUtils.isNotEmpty(tavolo.getDenominazione())) {
			query += " and t.denominazione like '%" + tavolo.getDenominazione() + "%' ";
		}
		if (tavolo.getDataCreazione() != null) {
			try {
				query += " and t.dataCreazione = '" + new SimpleDateFormat("yyyy-MM-dd").parse(tavolo.getDataCreazione().toString()) + "'";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return entityManager.createQuery(query, Tavolo.class).getResultList();
	}

	@Override
	@Transactional
	public List<Tavolo> findAll() {
		return (List<Tavolo>) tavoloRepository.findAll();
	}

	@Override
	@Transactional
	public List<Tavolo> findTavoloByExampleWithUser(Tavolo tavolo, User user, User userSession) {
		String query = "select t from User u right join u.tavolo t where 1=1 ";
		
		if(userSession != null) {
			query += " and t.esperienzaMinima <= " + userSession.getEsperienza();
		}
		if (tavolo.getCreditoMinimo() > 0) {
			query += " and t.creditoMinimo = " + tavolo.getCreditoMinimo();
		}
		if (StringUtils.isNotEmpty(tavolo.getDenominazione())) {
			query += " and t.denominazione like '%" + tavolo.getDenominazione() + "%' ";
		}
		if (tavolo.getDataCreazione() != null) {
			try {
				query += " and t.dataCreazione = '" + new SimpleDateFormat("yyyy-MM-dd").parse(tavolo.getDataCreazione().toString()) + "'";
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (user != null) {
			query += " and u.id = " + user.getId();    //NON FUNZIONA LA RICERCA PER GIOCATORE
		}
		
		return entityManager.createQuery(query, Tavolo.class).getResultList();
	}

}
