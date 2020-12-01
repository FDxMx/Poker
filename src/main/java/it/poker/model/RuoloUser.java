package it.poker.model;

import java.util.EnumSet;
import java.util.Set;

public enum RuoloUser {
	
	ADMIN_ROLE, PLAYER_ROLE, SPECIAL_PLAYER_ROLE;
	
	public static Set<RuoloUser> listaEnum(){
		Set<RuoloUser> ruoli = EnumSet.of(ADMIN_ROLE, PLAYER_ROLE, SPECIAL_PLAYER_ROLE);
		return ruoli;
	}

}
