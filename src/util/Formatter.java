package util;

import java.math.BigInteger;

public class Formatter {

	public static boolean isNumeric (String number) {
	    try {
	        Double.parseDouble(number.trim().replace(",", "."));
	    } catch (NumberFormatException | NullPointerException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public static int format (String string) {
	  int parsedNumber = null != string && 
	      !"".equals(string) &&
	      isNumeric(string) ? 
	      Integer.parseInt(string) : 0; 	  
	  return parsedNumber;
	}
	
	 public static double formatDouble (String string) {
	    double parsedNumber = null != string && 
	        !"".equals(string) &&
	        isNumeric(string) ? 
	        Double.parseDouble(string) : 0.0;     
	    return parsedNumber;
	  }

   public static String formatString (String string) {
     String parsedString =  null != string  ? string: ""; 
     return parsedString;
   }
  
   public static BigInteger formatBigInteger (String string) {
     BigInteger parsedNumber;
     int parsedToInt = null != string && 
         !"".equals(string) &&
         isNumeric(string) ? 
         Integer.parseInt(string) : 0; 
      parsedNumber = BigInteger.valueOf(parsedToInt);
     return parsedNumber;
   }

}
