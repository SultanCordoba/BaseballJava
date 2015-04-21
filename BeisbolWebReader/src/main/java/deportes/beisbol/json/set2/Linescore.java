
package deportes.beisbol.json.set2;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Linescore{
   	private GenericHA e;
   	private GenericHA h;
   	private GenericHA hr;
   	private List inning;
   	private GenericHA r;
   	private GenericHA sb;
   	private GenericHA so;

 	public GenericHA getE(){
		return this.e;
	}
	public void setE(GenericHA e){
		this.e = e;
	}
 	public GenericHA getH(){
		return this.h;
	}
	public void setH(GenericHA h){
		this.h = h;
	}
 	public GenericHA getHr(){
		return this.hr;
	}
	public void setHr(GenericHA hr){
		this.hr = hr;
	}
 	public List getInning(){
		return this.inning;
	}
	public void setInning(List inning){
		this.inning = inning;
	}
 	public GenericHA getR(){
		return this.r;
	}
	public void setR(GenericHA r){
		this.r = r;
	}
 	public GenericHA getSb(){
		return this.sb;
	}
	public void setSb(GenericHA sb){
		this.sb = sb;
	}
 	public GenericHA getSo(){
		return this.so;
	}
	public void setSo(GenericHA so){
		this.so = so;
	}
}
