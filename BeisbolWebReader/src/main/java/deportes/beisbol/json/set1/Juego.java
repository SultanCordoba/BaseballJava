package deportes.beisbol.json.set1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Juego {
	private String game_type;
	private String league;
	private String home_team_name;
	private String home_name_abbrev;
	private String away_team_name;
	private String away_name_abbrev;
	private Status status;
	private LineScore linescore;
	private String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LineScore getLinescore() {
		return linescore;
	}
	public void setLinescore(LineScore linescore) {
		this.linescore = linescore;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getGame_type() {
		return game_type;
	}
	public void setGame_type(String game_type) {
		this.game_type = game_type;
	}
	public String getLeague() {
		return league;
	}
	public void setLeague(String league) {
		this.league = league;
	}
	public String getHome_team_name() {
		return home_team_name;
	}
	public void setHome_team_name(String home_team_name) {
		this.home_team_name = home_team_name;
	}
	public String getHome_name_abbrev() {
		return home_name_abbrev;
	}
	public void setHome_name_abbrev(String home_name_abbrev) {
		this.home_name_abbrev = home_name_abbrev;
	}
	public String getAway_team_name() {
		return away_team_name;
	}
	public void setAway_team_name(String away_team_name) {
		this.away_team_name = away_team_name;
	}
	public String getAway_name_abbrev() {
		return away_name_abbrev;
	}
	public void setAway_name_abbrev(String away_name_abbrev) {
		this.away_name_abbrev = away_name_abbrev;
	}

}
