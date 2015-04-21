package deportes.beisbol.json.set1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LineScore {
	private GenericHA r;
	private GenericHA h;
	private GenericHA e;
	
	public GenericHA getR() {
		return r;
	}
	public void setR(GenericHA r) {
		this.r = r;
	}
	public GenericHA getH() {
		return h;
	}
	public void setH(GenericHA h) {
		this.h = h;
	}
	public GenericHA getE() {
		return e;
	}
	public void setE(GenericHA e) {
		this.e = e;
	}
	
	
}
