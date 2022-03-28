package com.parser.model;

public class Alert {
	private String id;
	 private String type;
	 private String host;
	
	private long duration;
	 private Boolean alertFlag;
	
	public boolean isAlertFlag() {
		return alertFlag;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public void setAlertFlag(boolean alertFlag) {
		this.alertFlag = alertFlag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}

	public Alert(Event eventLog, long duration){
		this.id=eventLog.getId();
		this.type=eventLog.getType();
		this.host=eventLog.getHost();
		this.duration=duration;
		this.alertFlag=Boolean.FALSE;		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alertFlag == null) ? 0 : alertFlag.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alert other = (Alert) obj;
		if (alertFlag == null) {
			if (other.alertFlag != null)
				return false;
		} else if (!alertFlag.equals(other.alertFlag))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
	       return this.id +" "+ this.host+" "+ this.duration+" "+ this.type +" "+ this.alertFlag;
	    }
}
