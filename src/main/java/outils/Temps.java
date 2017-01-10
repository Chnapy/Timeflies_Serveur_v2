/*
 * 
 * 
 * 
 */
package outils;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Temps.java
 * 
 */
public class Temps {
	
	private long timeInMillis;
	
	public Temps() {
		this(System.currentTimeMillis());
	}
	
	public Temps(long timeInMillis) {
		this.timeInMillis = timeInMillis;
	}
	
	public Temps addJours(int jours) {
		return addHeures(jours * 24);
	}
	
	public Temps addHeures(int heures) {
		return addMinutes(heures * 60);
	}
	
	public Temps addMinutes(int minutes) {
		return addSeconds(minutes * 60);
	}
	
	public Temps addSeconds(int seconds) {
		return addMillis(seconds * 1000);
	}
	
	public Temps addMillis(int millis) {
		this.timeInMillis += millis;
		return this;
	}

	@JsonValue
	public long getTimeInMillis() {
		return timeInMillis;
	}

}
