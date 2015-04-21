package deportes.beisbol.json.set1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeisbolJuegoJson {
	String subject;
	String copyright;
    DataGames data;
	
	public DataGames getData() {
		return data;
	}

	public void setData(DataGames data) {
		this.data = data;
	}

	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getCopyright() {
		return copyright;
	}
	
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
}
