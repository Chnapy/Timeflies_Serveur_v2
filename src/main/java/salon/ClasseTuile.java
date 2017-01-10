/*
 * 
 * 
 * 
 */
package salon;

import com.fasterxml.jackson.annotation.JsonGetter;
import java.util.Optional;
import map.Position;
import map.TypeTuile;

/**
 * ClasseTuile.java
 *
 */
public class ClasseTuile {

	private TypeTuile type;
	private Position position;
	private Optional<Integer> equipe;

	public ClasseTuile() {
		equipe = Optional.empty();
	}

	public ClasseTuile(int type, Position position) {
		this(type, position, -1);
	}

	public ClasseTuile(int type, Position position, int equipe) {
		this.type = TypeTuile.getFromId(type);
		this.position = position;
		this.equipe = Optional.of(equipe);
	}

	public TypeTuile getType() {
		return type;
	}

	public void setType(int type) {
		this.type = TypeTuile.getFromId(type);
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Optional<Integer> getEquipe() {
		return equipe;
	}

	public void setEquipe(int equipe) {
		this.equipe = Optional.of(equipe);
	}

	@JsonGetter("equipe")
	public Integer getJSONEquipe() {
		if (equipe.isPresent()) {
			return equipe.get();
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		return "ClasseTuile{" + "type=" + type + ", position=" + position + ", equipe=" + equipe + '}';
	}

}
