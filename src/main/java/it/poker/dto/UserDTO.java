package it.poker.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.poker.model.User;

public class UserDTO {
	
	private Long id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String dataRegistrazione;
	
	public UserDTO() {}

	public UserDTO(String nome, String cognome, String username, String password, String dataRegistrazione) {
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

	public String getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(String dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}
	
	public List<String> errors(){
		List<String> result = new ArrayList<String>();
		if(StringUtils.isBlank(this.nome))
			result.add("Il campo NOME non può essere vuoto");
		if(StringUtils.isBlank(this.cognome))
			result.add("Il campo COGNOME non può essere vuoto");
		if(StringUtils.isBlank(this.username))
			result.add("Il campo USERNAME non può essere vuoto");
		if(StringUtils.isBlank(this.password))
			result.add("Il campo PASSWORD non può essere vuoto");
		if(StringUtils.isBlank(this.dataRegistrazione))
			result.add("Il campo DATA REGISTRAZIONE non può essere vuoto");
		return result;
	}
	
	public static User buildModelFromDto(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setNome(userDTO.getNome());
		user.setCognome(userDTO.getCognome());
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		try {
			user.setDataRegistrazione(new SimpleDateFormat("yyyy-MM-dd").parse(userDTO.getDataRegistrazione()));
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("LE DATEEEE!!!!!!");
		}
		return user;
	}

}
