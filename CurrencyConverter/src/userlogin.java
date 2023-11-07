import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class userlogin {

	private static String[] args = new String[1];
	
	// file where users login information is stored
	public static final String UserInfoFile = "UserInfo.txt";
	
	// array of size 2 containing a given user's username and password
	public static String[] userInfo = new String[2];
	
	// username and password from user's input when attempting to login (both equal .getInput of login ComboBoxes)
	private static String loginUsername;
	private static String loginPassword;
	
	private static String loginErrorMessage = "The user name or password you entered may be incorrect, please try again.";
	
	
	public static void validUser() throws FileNotFoundException
	{
		
		Scanner file = new Scanner(new File (UserInfoFile));
		
		while(file.hasNextLine())
		{
			userInfo = file.nextLine().trim().split("-");
			
			if(loginUsername == userInfo[0] && loginPassword == userInfo[1])
			{
				file.close();
				Driver.main(args);
			}
		}
		
		file.close();
		
		// if given username and password are invalid, print the login error message
		System.out.println(loginErrorMessage);
		
		
	}

}
