
package deportes.beisbol.json.set2;

import java.util.List;

public class Links{
   	private String away_recap_link;
   	private String away_team;
   	private String box;
   	private String home_recap_link;
   	private String home_team;

 	public String getAway_recap_link(){
		return this.away_recap_link;
	}
	public void setAway_recap_link(String away_recap_link){
		this.away_recap_link = away_recap_link;
	}
 	public String getAway_team(){
		return this.away_team;
	}
	public void setAway_team(String away_team){
		this.away_team = away_team;
	}
 	public String getBox(){
		return this.box;
	}
	public void setBox(String box){
		this.box = box;
	}
 	public String getHome_recap_link(){
		return this.home_recap_link;
	}
	public void setHome_recap_link(String home_recap_link){
		this.home_recap_link = home_recap_link;
	}
 	public String getHome_team(){
		return this.home_team;
	}
	public void setHome_team(String home_team){
		this.home_team = home_team;
	}
}
