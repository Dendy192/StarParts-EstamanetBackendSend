package id.git.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class test {
	public static void main(String[] args) throws ParseException {
//		DecimalFormat idr = (DecimalFormat) DecimalFormat.getCurrencyInstance();
		String tmp = "2173871.5882";
		Double saldo = Double.valueOf(tmp);
		System.out.println(saldo);
		String tmp1 = "2023-10-16";
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("Testing "+sdf1.parse(tmp1));
		Date date1 = sdf1.parse(tmp1);
		System.out.println(date1);
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println(sdf2.format(date1));
		DecimalFormat dFormat = new DecimalFormat("###,###,###");
		Locale indo = new Locale("in","ID");
		NumberFormat format = NumberFormat.getCurrencyInstance(indo);
		System.out.println(format.format(saldo));
		System.out.println(dFormat.format(saldo));
	}
}
