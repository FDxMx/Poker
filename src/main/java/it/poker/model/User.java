package it.poker.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	@Temporal(TemporalType.DATE)
	private Date dataRegistrazione;
	private Integer esperienza;
	private Integer credito;
	@Enumerated(EnumType.STRING)
	private StatoUser stato;
	@ElementCollection(targetClass = RuoloUser.class)
	@CollectionTable(name = "user_ruolo", joinColumns = @JoinColumn(name = "user_fk"))
	@Enumerated(EnumType.STRING)
	@Column(name = "ruolo")
	private List<RuoloUser> ruoli;
	@OneToOne(mappedBy = "user")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tavolo_fk", nullable = true)
	private Tavolo tavolo;
	
	
	public User() {}

	public User(String nome, String cognome, String username, String password, Date dataRegistrazione) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.dataRegistrazione = dataRegistrazione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public Integer getEsperienza() {
		return esperienza;
	}

	public void setEsperienza(Integer esperienza) {
		this.esperienza = esperienza;
	}

	public Integer getCredito() {
		return credito;
	}

	public void setCredito(Integer credito) {
		this.credito = credito;
	}

	public StatoUser getStato() {
		return stato;
	}

	public void setStato(StatoUser stato) {
		this.stato = stato;
	}

	public List<RuoloUser> getRuoli() {
		return ruoli;
	}

	public void setRuoli(List<RuoloUser> ruoli) {
		this.ruoli = ruoli;
	}

	public Tavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}

	@Override
	public String toString() {
		return "id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", username=" + username
				+ ", dataRegistrazione=" + dataRegistrazione + ", esperienza=" + esperienza + ", credito=" + credito;
	}
}
