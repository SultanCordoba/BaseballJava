package deportes.beisbol.utils;

import java.util.ArrayList;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class UrlUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(UrlUtils.class);
	
	public static TreeMap<String, String> construyeBreadcrumb(String urlString, 
			TreeMap<String, String> auxiliar) {
		TreeMap<String, String> resultado = new TreeMap<>();
		
		int iEstado = 0;
		
		Splitter splitter = Splitter.on("/").omitEmptyStrings();
		ArrayList<String> ligaRegreso = new ArrayList<>();
		
		ArrayList<String> menuPrevio = Lists.newArrayList(splitter.split(urlString));
		
		Joiner joiner = Joiner.on("/").skipNulls();
		
		for (String menuComponente : menuPrevio) {
			switch (menuComponente.toUpperCase()) {
			case "BASEBALL":
				resultado.put("/inicio/", "header.inicio");
				break;
				
			case "LIGA":
				iEstado = 1;
				resultado.put("/liga/showall", "header.ligas");
				ligaRegreso.add("liga");
				break;
				
			case "SHOW":
			case "SHOWALL":
				// resultado.remove(resultado.lastKey());
				return resultado;	
				
			default:
				switch(iEstado) {
				case 1:
					try {
						ligaRegreso.add(menuComponente);
						ligaRegreso.add("show");
						ligaRegreso.add(auxiliar.get("zonaLiga"));
						
						resultado.put("X/" + joiner.join(ligaRegreso).toString(),
								auxiliar.get("ligaSiglas"));
						
						ligaRegreso.remove(ligaRegreso.size() - 1);
						ligaRegreso.remove(ligaRegreso.size() - 1);

					} catch (NullPointerException npe) {}
					iEstado = 0;
					break;
				}
					
			}
			
			
		}
		
		return resultado;
	}
}
