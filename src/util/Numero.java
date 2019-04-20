package util;

public class Numero {

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
}
