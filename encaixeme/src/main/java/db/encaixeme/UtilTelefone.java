package db.encaixeme;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilTelefone {
	
	public static String executar() {
		final StringBuilder builder = new StringBuilder();
		for ( int contador = 1; contador < 3; contador++ ) {
			builder.append(new Integer((int) (Math.random() * 10)).toString());
		}
		builder.append("9");
		for ( int contador = 1; contador < 9; contador++ ) {
			builder.append(new Integer((int) (Math.random() * 10)).toString());
		}
		return builder.toString();
	}

	public static String removeCaracteresDDD(String ddd) {
		return ddd
				.replace("(", " ")
				.replace(")", " ")
				.replaceAll(" ", "")
				.trim();
	}
	
	public static String removeCaracteresTelefone(String telefone) {
		return telefone
				  .replace("-", " ")
				  .replaceAll(" ", "")
				  .trim();
	}
	
	public static void main(String[] args) throws ParseException {
		System.out.println(removeCaracteresTelefone("9-9828-0409"));
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:MM:ss");
		calendar.setTime(sdf.parse("17/12/2017 23:00:00"));
		System.out.println("Parse: " + sdf.parse("17/12/2017 23:00:00"));
		calendar.add(Calendar.MINUTE, 30);
		System.out.println(calendar.getTime());
		Date data = new Date();
		if ( calendar.getTime().after(data) ) {
			System.out.println("Calendar: " + calendar.getTime());
			System.out.println("Data: " + data);
			System.out.println("Calendar after data");
		} else {
			System.out.println("Calendar: " + calendar.getTime());
			System.out.println("Calendar: " + data);
			System.out.println("calendar before data");
		}
	}
}