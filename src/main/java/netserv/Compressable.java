/*
 * 
 * 
 * 
 */

package netserv;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Compressable
 * Interface
 */
public interface Compressable {
	
	@JsonIgnore
	public Compressed getCompressed();

}
