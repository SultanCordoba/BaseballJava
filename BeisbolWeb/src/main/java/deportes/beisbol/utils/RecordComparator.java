package deportes.beisbol.utils;

import java.util.Comparator;

import com.google.common.base.Strings;

import deportes.beisbol.model.RecordBeisbol;

public class RecordComparator implements Comparator<RecordBeisbol> {
	
	@Override
	public int compare(RecordBeisbol o1, RecordBeisbol o2) {
    	return o1.getIdVuelta() < o2.getIdVuelta() ?
    			-1 : o1.getIdVuelta() > o2.getIdVuelta() ?
    			1 : doSecondaryOrderSort(o1, o2);
    }
    
    private int doSecondaryOrderSort(RecordBeisbol o1, RecordBeisbol o2) {
    	return Strings.nullToEmpty(o1.getNombreGrupo()).compareToIgnoreCase
    			  (Strings.nullToEmpty(o2.getNombreGrupo())) != 0 ?
        		Strings.nullToEmpty(o1.getNombreGrupo()).compareToIgnoreCase
        		   (Strings.nullToEmpty(o2.getNombreGrupo())) :		            	
        		doTertiaryOrderSort(o1,o2);
    }

    private int doTertiaryOrderSort(RecordBeisbol o1, RecordBeisbol o2) {
        return o1.getPorcentaje()>o2.getPorcentaje() ?
        		-1 : o1.getPorcentaje()<o2.getPorcentaje() ? 1:0;
    }
}
