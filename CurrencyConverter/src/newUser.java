import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class newUser {

	// username and password from user's input when creating new account (both equal .getInput of new user ComboBoxes)
	private static String newUserUsername;
	private static String newUserPassword;
	
	private static String newUsernameErrorMessage = "That username already exists, please enter a new one.";
	private static String newPasswordErrorMessage = "That password already exists, please enter a new one.";
	
	
	private static void newUser() throws IOException
	{
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(userlogin.UserInfoFile, true));
		
		Scanner file = new Scanner(new File (userlogin.UserInfoFile));
		
		while(file.hasNextLine())
		{
			userlogin.userInfo = file.nextLine().trim().split("-");
			
			
			if(newUserUsername == userlogin.userInfo[0])
			{
				System.out.println(newUsernameErrorMessage);
			}
			else if(newUserPassword == userlogin.userInfo[1])
			{
				System.out.println(newPasswordErrorMessage);
			}
			else if(newUserUsername == userlogin.userInfo[0] &&
					newUserPassword == userlogin.userInfo[1])
			{
				System.out.println(newUsernameErrorMessage);
				System.out.println(newPasswordErrorMessage);
			}
		}
		
		writer.newLine();
		
		// user info is stored into UserInfoFile in the format "username-password"
		writer.write(newUserUsername + "-" + newUserPassword);
		writer.flush();
		
	}
	
}
