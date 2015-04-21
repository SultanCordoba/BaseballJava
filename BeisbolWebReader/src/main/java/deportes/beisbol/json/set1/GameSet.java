package deportes.beisbol.json.set1;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameSet {
	String year;
	String month;
	String day;
	Set<Juego> game;
	
	public Set<Juego> getGame() {
		return game;
	}

	public void setGame(Set<Juego> game) {
		this.game = game;
	}

	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public String getMonth() {
		return month;
	}
	
	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getDay() {
		return day;
	}
	
	public void setDay(String day) {
		this.day = day;
	}
}
