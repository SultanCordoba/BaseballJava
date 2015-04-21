package deportes.beisbol.json.set1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Status {
	String status;
	int inning;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getInning() {
		return inning;
	}
	public void setInning(int inning) {
		this.inning = inning;
	}
	
	
}
