package it.poker.model;

import java.util.EnumSet;
import java.util.Set;

public enum StatoUser {
	
	CREATO, ABILITATO, DISABILITATO;
	
	public static Set<StatoUser> listaEnum(){
		Set<StatoUser> stati = EnumSet.of(CREATO, ABILITATO, DISABILITATO);
		return stati;
	}

}
