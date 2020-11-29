package it.poker.service.tavolo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.poker.model.Tavolo;
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
	public List<Tavolo> findTavoloByExample(Tavolo tavolo) {
		String query = "select t from Tavolo t where 1=1 ";

		if (tavolo.getEsperienzaMinima() > 0)
			query += " and t.esperienzaMinima = " + tavolo.getEsperienzaMinima();
		if (tavolo.getEsperienzaMinima() > 0)
			query += " and t.creditoMinimo = " + tavolo.getCreditoMinimo();
		if (StringUtils.isNotEmpty(tavolo.getDenominazione()))
			query += " and t.denominazione like '%" + tavolo.getDenominazione() + "%' ";
		if (tavolo.getDataCreazione() != null)
			query += " and t.dataCreazione = " + tavolo.getDataCreazione();

		return entityManager.createQuery(query, Tavolo.class).getResultList();
	}

}
