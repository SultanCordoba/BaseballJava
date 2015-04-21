
package deportes.beisbol.json.set2;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameSet{
   	private String day;
   	private Juego game;
   	private String modified_date;
   	private String month;
   	private String next_day_date;
   	private String year;

 	public String getDay(){
		return this.day;
	}
	public void setDay(String day){
		this.day = day;
	}
 	public Juego getGame(){
		return this.game;
	}
	public void setGame(Juego game){
		this.game = game;
	}
 	public String getModified_date(){
		return this.modified_date;
	}
	public void setModified_date(String modified_date){
		this.modified_date = modified_date;
	}
 	public String getMonth(){
		return this.month;
	}
	public void setMonth(String month){
		this.month = month;
	}
 	public String getNext_day_date(){
		return this.next_day_date;
	}
	public void setNext_day_date(String next_day_date){
		this.next_day_date = next_day_date;
	}
 	public String getYear(){
		return this.year;
	}
	public void setYear(String year){
		this.year = year;
	}
}
