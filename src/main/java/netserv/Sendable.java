/*
 * 
 * 
 * 
 */
package netserv;

import java.util.ArrayList;
import java.util.List;

/**
 * Sendable.java
 * 
 */
public abstract class Sendable {
	
	private boolean success;
	private List<String> errorMsg;

	public Sendable() {
		this(false);
	}

	public Sendable(boolean success) {
		this.success = success;
		this.errorMsg = new ArrayList();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<String> getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(List<String> errorMsg) {
		this.errorMsg = errorMsg;
	}

}
