package gritLogin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
	
	public static boolean valEmail(String user) {
		
		boolean valid = false;
		if (null != user) {         
			//requires a valid email
			String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(user);
 
            if (matcher.matches()) valid = true;
            
        }
									//alternative email validator___________________________
							        boolean javax = EmailValidator.emailValidator(user);
							        System.out.println("Javax says validation to be " + javax);
							        //_________________________________________________________
							        
		return valid;
	}
	
	public static boolean valPassw(String passw) {
		
		if (null != passw) { 
			
		//requires 2 upper case, 2 lower case, 2 numbers, 1 special char and NO spaces
	     String regex ="^(?=.{7,}$)(?=(?:.*[A-Z]){2})(?=.*[a-z]{2})(?=(?:.*[0-9]){2})(?=.*[@#$%^&+=*©£∞§!€])(?=\\S+$).*";
	     Pattern pattern = Pattern.compile(regex);
         Matcher matcher = pattern.matcher(passw);
         
	     if(matcher.matches())return true;
		}
	     return false;
	}
	
	//checks credentials, very static at the moment
	public static boolean checkLogin(String user, String passw) throws IOException {
		 
		Path pathToFile = Paths.get("file.txt"); 

		try (
				// create an instance of BufferedReader 
				BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) { 
			
				// read the first line from the text file 
				String line = br.readLine(); 
				
				// loop until all lines are read 
				while (line != null) { 
			 
					String[] attributes = line.split("#");
					line = br.readLine(); 
					
					//matching users & passwords
					if(user.equals(attributes[0].toString()) && passw.equals(attributes[1].toString()))return true;
					}
			} catch (IOException ioe) { ioe.printStackTrace(); 
		} 
		
		
		return false;
	}
	
	//write to file
	public static void addUser(String user, String passw) throws IOException {

		//open or create file
		File yourFile = new File("file.txt");
		yourFile.createNewFile(); // if file already exists will do nothing	
		
		//writes to file
	    BufferedWriter out = new BufferedWriter(new FileWriter("file.txt", true)); 
	    out.write(user + "#" + passw);
	    out.newLine();
	    out.close();
	}
	
}
