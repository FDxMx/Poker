package it.poker.service.tavolo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
	public List<Tavolo> findTavoloByExample(Tavolo tavolo, User userSession) {
		
		String query = "select t from Tavolo t join t.creatoreTavolo u where 1=1 ";
		
		if (userSession != null) {
			query += " and u.id = :id";
		}
		if (tavolo.getEsperienzaMinima() > 0 && tavolo.getEsperienzaMinima() != null) {
			query += " and t.esperienzaMinima = :esperienzaMinima";
		}
		if (tavolo.getCreditoMinimo() > 0 && tavolo.getCreditoMinimo() != null) {
			query += " and t.creditoMinimo = :creditoMinimo";
		}
		if (StringUtils.isNotEmpty(tavolo.getDenominazione())) {
			query += " and t.denominazione like :denominazione";
		}
		if (tavolo.getDataCreazione() != null) {
			query += " and t.dataCreazione = :dataCreazione";
		}
		TypedQuery<Tavolo> query2 = entityManager.createQuery(query, Tavolo.class);
		
		if (userSession != null) {
			query2.setParameter("id", userSession.getId());
		}
		if (tavolo.getEsperienzaMinima() > 0 && tavolo.getEsperienzaMinima() != null) {
			query2.setParameter("esperienzaMinima", tavolo.getEsperienzaMinima());
		}
		if (tavolo.getCreditoMinimo() > 0 && tavolo.getCreditoMinimo() != null) {
			query2.setParameter("creditoMinimo", tavolo.getCreditoMinimo());
		}
		if (StringUtils.isNotEmpty(tavolo.getDenominazione())) {
			query2.setParameter("denominazione","%" + tavolo.getDenominazione() + "%");
		}
		if (tavolo.getDataCreazione() != null) {
			query2.setParameter("dataCreazione", tavolo.getDataCreazione());
		}
		return query2.getResultList();
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
		
		if (userSession != null) {
			query += " and t.esperienzaMinima <= :esperienzaMinima";
		}
		if (tavolo.getCreditoMinimo() > 0 && tavolo.getCreditoMinimo() != null) {
			query += " and t.creditoMinimo = :creditoMinimo";
		}
		if (StringUtils.isNotEmpty(tavolo.getDenominazione())) {
			query += " and t.denominazione like :denominazione";
		}
		if (tavolo.getDataCreazione() != null) {
			query += " and t.dataCreazione = :dataCreazione";
		}
		if (user != null) {
			query += " and u.id = :id"; 
		}
		TypedQuery<Tavolo> query2 = entityManager.createQuery(query, Tavolo.class);
		
		if(userSession != null) {
			query2.setParameter("esperienzaMinima", userSession.getEsperienza());
		}
		if (tavolo.getCreditoMinimo() > 0 && tavolo.getCreditoMinimo() != null) {
			query2.setParameter("creditoMinimo", tavolo.getCreditoMinimo());
		}
		if (StringUtils.isNotEmpty(tavolo.getDenominazione())) {
			query2.setParameter("denominazione", "%" + tavolo.getDenominazione() + "%");
		}
		if (tavolo.getDataCreazione() != null) {
			query2.setParameter("dataCreazione", tavolo.getDataCreazione());
		}
		if (user != null) {
			query2.setParameter("id", user.getId()); 
		}
		return query2.getResultList();
	}
}
