package it.poker.service.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.poker.model.RuoloUser;
import it.poker.model.User;
import it.poker.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

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
	public List<User> findUserByTavolo(long idTavolo) {
		return userRepository.findUserByTavolo(idTavolo);
	}

	@Override
	@Transactional
	public User findById(long idUser) {
		return userRepository.findById(idUser).orElse(null);
	}

	@Override
	public void update(User user) {
		userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User findUserWithRuoli(long idUser) {
		return userRepository.findUserWithRuoli(idUser);
	}
	
	@Override
	@Transactional
	public List<User> findUserByExample(User user, RuoloUser ruolo) {
		
		String query = "select distinct u from User u left join fetch u.ruoli r where 1=1 ";
		
		if (StringUtils.isNotEmpty(user.getNome())) {
			query += " and u.nome like :nome";
		}
		if (StringUtils.isNotEmpty(user.getCognome())) {
			query += " and u.cognome like :cognome";
		}
		if (StringUtils.isNotEmpty(user.getUsername())) {
			query += " and u.username like :username";
		}
		if (user.getDataRegistrazione() != null) {
			query += " and u.dataRegistrazione = :dataRegistrazione";
		}
		if (user.getStato() != null) {
			query += " and u.stato = :stato";
		}
		if (ruolo != null) {
			query += " and r = :ruolo";
		}
		TypedQuery<User> query2 = entityManager.createQuery(query, User.class);
		
		if (StringUtils.isNotEmpty(user.getNome())) {
			query2.setParameter("nome", "%" + user.getNome() + "%");
		}
		if (StringUtils.isNotEmpty(user.getCognome())) {
			query2.setParameter("cognome", "%" + user.getCognome() + "%");
		}
		if (StringUtils.isNotEmpty(user.getUsername())) {
			query2.setParameter("username", "%" + user.getUsername() + "%");
		}
		if (user.getDataRegistrazione() != null) {
			query2.setParameter("dataRegistrazione", user.getDataRegistrazione());
		}
		if (user.getStato() != null) {
			query2.setParameter("stato", user.getStato());
		}
		if (ruolo != null) {
			query2.setParameter("ruolo", ruolo);
		}
		return query2.getResultList();
	}

}
