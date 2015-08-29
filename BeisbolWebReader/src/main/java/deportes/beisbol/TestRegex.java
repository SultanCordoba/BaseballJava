package deportes.beisbol;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class TestRegex {	
	
	public static void main(String[] args) {
		
		
		
		for (Month mes : Month.values()) {
			System.out.println(mes.getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("es")));
		}
	}
}
