package gritLogin;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

//Java program to validate email in Java
public class EmailValidator
{	

	 public static boolean emailValidator(String email) {
	    boolean isValid = false;
	    try {
	        //
	        // Create InternetAddress object and validated the supplied
	        // address which is this case is an email address.
	        InternetAddress internetAddress = new InternetAddress(email);
	        internetAddress.validate();
	        isValid = true;
	    } catch (AddressException e) {
	        System.out.println("You are in catch block -- Exception Occurred for: " + email);
	    }
	    return isValid;
	}
}
