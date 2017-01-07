/*
 * 
 * 
 * 
 */
package salon.proprietes;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * TypePropriete.java
 * 
 */
public enum TypePropriete {
	
	MAP,
	VISIBILITE,
	TYPECOMBAT,
	NBR_PERSOS_CLASSE,
	
	//CPS
	NBR_PERSOS_TOTAUX,
	
	//Equipe
	NBR_EQUIPES_MAX,
	NBR_PERSOS_EQUIPE,
	NBR_PERSOS_CLASSE_EQUIPE,
	NBR_PERSOS_JOUEUR_EQUIPE
	
	;
	
	private TypePropriete() {
	}

	@JsonValue
	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
	
}
