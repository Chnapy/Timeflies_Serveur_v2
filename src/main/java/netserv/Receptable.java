/*
 * 
 * 
 * 
 */
package netserv;

/**
 * Receptable.java
 * 
 */
public abstract class Receptable {
	
	private long sendTime;

	public long getSendTime() {
		return sendTime;
	}

	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}

	@Override
	public String toString() {
		return "sendTime=" + sendTime;
	}

}
