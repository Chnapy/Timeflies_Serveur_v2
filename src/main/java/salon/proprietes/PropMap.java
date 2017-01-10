/*
 * 
 * 
 * 
 */
package salon.proprietes;

import java.util.Set;
import java.util.stream.Collectors;
import salon.ClasseMap;
import salon.Salon;

/**
 * PropMap.java
 *
 */
public class PropMap extends ProprieteLimite<ClasseMap, Integer> {

	private static final int MIN = 1;

	public PropMap(Salon salon) {
		super(salon, TypePropriete.MAP, null, MIN, null);
	}

	@Override
	protected ClasseMap objectToValeur(Integer newValeur) throws IllegalArgumentException {
		return this.salon.getModele().getClasseMapFromId(newValeur)
				.<IllegalArgumentException>orElseThrow(IllegalArgumentException::new);
	}

	@Override
	protected void afterSet() {
		ClasseMap map = getValeur();
		
		Set<Integer> typecombats = map.getTypecombat().stream().map(t -> t.getId()).collect(Collectors.toSet());
		int nbr_equipe_max = map.getNbrequipemax();
		int nbr_perso_equipe_max = map.getNbrpersosequipemax();
		
		PropTypeCombat ptc = salon.<PropTypeCombat>getProp(TypePropriete.TYPECOMBAT);
		ptc.setMin(null);
		ptc.setMax(null);
		ptc.setWhitelist(typecombats);
		
		salon.<PropNbrEquipe>getProp(TypePropriete.NBR_EQUIPES_MAX).setMax(nbr_equipe_max);
		
		salon.<PropNbrPersosEquipeMax>getProp(TypePropriete.NBR_PERSOS_EQUIPE).setMax(nbr_perso_equipe_max);
	}

}
