package deportes.beisbol;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class ValidadorFecha {
	private String anyo;
	private String mes;
	private String dia;
	
	private Pattern pattern;
	private Matcher matcher;
	private Locale locale;
	
	private final String VALIDA_FECHA = "^\\d{0,2}-?\\w{0,3}-?\\d{0,4}$";
	private final String VALIDA_NUMERO = "^\\d+$";
	
	// private static final Logger logger = LoggerFactory.getLogger(ValidadorFecha.class);
	
	public ValidadorFecha(String prueba) {
		locale = Locale.forLanguageTag("es-MX");
		evaluar(prueba);
	}

	public void evaluar(String prueba) {
		anyo = "";
		mes = "";
		dia = "";
		calcular(prueba.toUpperCase());		
	}
	
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	public int getAnyo() {
		return anyo.isEmpty() ? 0 : Integer.valueOf(anyo);		
	}

	/* public int getMes() {
		
		if (mes.isEmpty()) return 0; 
		
		try {
			Date paso = new SimpleDateFormat("MMM", locale).parse(mes);
			LocalDateTime dtPaso = LocalDateTime.ofInstant(paso.toInstant(), ZoneId.systemDefault());
			return dtPaso.getMonthValue();
		} catch (ParseException e) {
			return 13;
		}
	} */
	
	public Collection<Integer> getMes() {		
		if (mes.isEmpty()) {
			return new HashSet<>();
		}

		HashSet<Integer> resultado = new HashSet<>();
		String mesCorto = "";
		
		for (Month mesEnum : Month.values()) {
			
			mesCorto = mesEnum.getDisplayName(TextStyle.SHORT, locale).toUpperCase();
			
			if (mesCorto.contains(mes)) {
				resultado.add(mesEnum.getValue());
			}
			
		}
		
		if (resultado.isEmpty()) {
			resultado.add(13);
		}
		
		return resultado; 
	}

	public int getDia() {
		return dia.isEmpty() ? 0 : Integer.valueOf(dia);
	}
	
	private void probarNumero(String prueba) {
		pattern = Pattern.compile(VALIDA_NUMERO);
		matcher = pattern.matcher(prueba);
		
		if (matcher.find()) {
			anyo = prueba;
			
			if (prueba.length() < 3) {
				dia = prueba;
			}
		}
		else {
			mes = prueba;
		}		
	}
	
	private void calcular(String fecha) {
		pattern = Pattern.compile(VALIDA_FECHA);
		matcher = pattern.matcher(fecha);
		
		if (matcher.find()) {
			if (fecha.contains("-")) {
				Splitter splitter = Splitter.on("-").omitEmptyStrings().trimResults();
				ArrayList<String> fechaArr = Lists.newArrayList(splitter.split(fecha));
				
				switch(fechaArr.size()) {
				case 3:
					probarNumero(fechaArr.get(2));
					probarNumero(fechaArr.get(1));
					probarNumero(fechaArr.get(0));
					break;
				
				case 2:
					String diaTemp = "";
					probarNumero(fechaArr.get(0));
					if (!anyo.isEmpty()) {
						anyo = "";
						diaTemp = dia;
					}					
					probarNumero(fechaArr.get(1));
					if (diaTemp.isEmpty()) {
						dia = "";
					}
					break;
					
				case 1:
					probarNumero(fechaArr.get(0));
					
				default:
					break;
				}
				
			}
			else {
				probarNumero(fecha);		
			}
		}
	}

}
