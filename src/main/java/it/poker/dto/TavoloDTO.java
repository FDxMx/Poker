package it.poker.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.poker.model.Tavolo;
import it.poker.model.User;

public class TavoloDTO {
	
	private Long id;
	private String esperienzaMinima;
	private String creditoMinimo;
	private String denominazione;
	private String dataCreazione;
	private User user;
	
	public TavoloDTO() {}

	public TavoloDTO(String esperienzaMinima, String creditoMinimo, String denominazione, String dataCreazione) {
		super();
		this.esperienzaMinima = esperienzaMinima;
		this.creditoMinimo = creditoMinimo;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
	}
	
	public TavoloDTO(String esperienzaMinima, String creditoMinimo, String dataCreazione) {
		super();
		this.esperienzaMinima = esperienzaMinima;
		this.creditoMinimo = creditoMinimo;
		this.dataCreazione = dataCreazione;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEsperienzaMinima() {
		return esperienzaMinima;
	}

	public void setEsperienzaMinima(String esperienzaMinima) {
		this.esperienzaMinima = esperienzaMinima;
	}

	public String getCreditoMinimo() {
		return creditoMinimo;
	}

	public void setCreditoMinimo(String creditoMinimo) {
		this.creditoMinimo = creditoMinimo;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> errors(){
		List<String> result = new ArrayList<String>();
		if(StringUtils.isBlank(this.esperienzaMinima))
			result.add("Il campo ESPERIENZA MINIMA non può essere vuoto");
		if(StringUtils.isBlank(this.creditoMinimo))
			result.add("Il campo CREDITO MINIMO non può essere vuoto");
		if(StringUtils.isBlank(this.denominazione))
			result.add("Il campo DENOMINAZIONE non può essere vuoto");
		if(StringUtils.isBlank(this.dataCreazione))
			result.add("Il campo DATA CREAZIONE non può essere vuoto");
		return result;
	}
	
	public List<String> errorsSearch(){
		List<String> result = new ArrayList<String>();
		if(!StringUtils.isBlank(this.esperienzaMinima) && !StringUtils.isNumeric(this.esperienzaMinima)) {
			result.add("Il campo ESPERIENZA MINIMA deve essere un numero!");
		}
		if(!StringUtils.isBlank(this.creditoMinimo) && !StringUtils.isNumeric(this.creditoMinimo)) {
			result.add("Il campo CREDITO MINIMO deve essere un numero!");
		}
		if(!StringUtils.isBlank(this.dataCreazione)) {
			try {
				new SimpleDateFormat("yyyy-MM-dd").parse(this.dataCreazione);
			} catch (ParseException e) {
				result.add("Il campo DATA CREAZIONE non è valido!");
			}
		}
		return result;
	}
	
//	public List<String> errorsSearchWithUser(){
//		List<String> result = new ArrayList<String>();
//		if(!StringUtils.isBlank(this.creditoMinimo) && !StringUtils.isNumeric(this.creditoMinimo)) {
//			result.add("Il campo CREDITO MINIMO deve essere un numero!");
//		}
//		if(!StringUtils.isBlank(this.dataCreazione)) {
//			try {
//				new SimpleDateFormat("yyyy-MM-dd").parse(this.dataCreazione);
//			} catch (ParseException e) {
//				result.add("Il campo DATA CREAZIONE non è valido!");
//			}
//		}
//		return result;
//	}
	
	public static Tavolo buildModelFromDto(TavoloDTO tavoloDTO) {
		Tavolo tavolo = new Tavolo();
		tavolo.setId(tavoloDTO.getId());
		tavolo.setEsperienzaMinima(tavoloDTO.getEsperienzaMinima() != null && !tavoloDTO.getEsperienzaMinima().equals("") ? Integer.parseInt(tavoloDTO.getEsperienzaMinima()) : 0);
		tavolo.setCreditoMinimo(tavoloDTO.getCreditoMinimo() != null && !tavoloDTO.getCreditoMinimo().equals("") ? Integer.parseInt(tavoloDTO.getCreditoMinimo()) : 0);
		tavolo.setDenominazione(tavoloDTO.getDenominazione());
		try {
			tavolo.setDataCreazione(tavoloDTO.getDataCreazione() != null && !tavoloDTO.getDataCreazione().equals("") ? new SimpleDateFormat("yyyy-MM-dd").parse(tavoloDTO.getDataCreazione()) : null);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tavolo;
	}

}
