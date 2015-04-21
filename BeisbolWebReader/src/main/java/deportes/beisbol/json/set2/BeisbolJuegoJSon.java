
package deportes.beisbol.json.set2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeisbolJuegoJSon{
   	private String copyright;
   	private DataGames data;
   	private String subject;

 	public String getCopyright(){
		return this.copyright;
	}
	public void setCopyright(String copyright){
		this.copyright = copyright;
	}
 	public DataGames getData(){
		return this.data;
	}
	public void setData(DataGames data){
		this.data = data;
	}
 	public String getSubject(){
		return this.subject;
	}
	public void setSubject(String subject){
		this.subject = subject;
	}
}
