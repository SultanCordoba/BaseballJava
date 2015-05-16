package deportes.beisbol.utils;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public class JugadorComparator implements Comparator<RosterBeisbol> {

	private Locale locale;
	
	public JugadorComparator(Locale locale) {
		this.locale = locale;
	}
	
	@Override
	public int compare(RosterBeisbol o1, RosterBeisbol o2) {
		
		Collator myCollator = Collator.getInstance(locale);
		
		int paso = myCollator.compare(o1.getJugador().getApellidoPaterno(), o2.getJugador().getApellidoPaterno());
		
		if (paso == 0) {
			return myCollator.compare(o1.getJugador().getNombre(), o2.getJugador().getNombre());
		}
		else {
			return paso;
		}
	}

}
