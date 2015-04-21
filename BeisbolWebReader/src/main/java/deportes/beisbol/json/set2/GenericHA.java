package deportes.beisbol.json.set2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericHA {
	private int home;
	private int away;
	private int diff;
	
	public int getHome() {
		return home;
	}
	public void setHome(int home) {
		this.home = home;
	}
	public int getAway() {
		return away;
	}
	public void setAway(int away) {
		this.away = away;
	}
	public int getDiff() {
		return diff;
	}
	public void setDiff(int diff) {
		this.diff = diff;
	}
	
	
}
