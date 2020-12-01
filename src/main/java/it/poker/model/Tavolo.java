package it.poker.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tavolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer esperienzaMinima;
	private Integer creditoMinimo;
	private String denominazione;
	@Temporal(TemporalType.DATE)
	private Date dataCreazione;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tavolo", orphanRemoval = true)
	private List<User> giocatori;
	@OneToOne
	@JoinColumn(name = "user_creatore_fk", nullable = false)
	private User creatoreTavolo;
	
	public Tavolo() {}

	public Tavolo(int esperienzaMinima, int creditoMinimo, String denominazione, Date dataCreazione, User creatoreTavolo) {
		super();
		this.esperienzaMinima = esperienzaMinima;
		this.creditoMinimo = creditoMinimo;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
		this.creatoreTavolo = creatoreTavolo;
	}
	
	public Tavolo(int esperienzaMinima, int creditoMinimo, String denominazione, Date dataCreazione) {
		super();
		this.esperienzaMinima = esperienzaMinima;
		this.creditoMinimo = creditoMinimo;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
	}
	
	public Tavolo(int creditoMinimo, String denominazione, Date dataCreazione) {
		super();
		this.creditoMinimo = creditoMinimo;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getEsperienzaMinima() {
		return esperienzaMinima;
	}

	public void setEsperienzaMinima(Integer esperienzaMinima) {
		this.esperienzaMinima = esperienzaMinima;
	}

	public Integer getCreditoMinimo() {
		return creditoMinimo;
	}

	public void setCreditoMinimo(Integer creditoMinimo) {
		this.creditoMinimo = creditoMinimo;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public List<User> getGiocatori() {
		return giocatori;
	}

	public void setGiocatori(List<User> giocatori) {
		this.giocatori = giocatori;
	}

	public User getCreatoreTavolo() {
		return creatoreTavolo;
	}

	public void setCreatoreTavolo(User creatoreTavolo) {
		this.creatoreTavolo = creatoreTavolo;
	}

}
