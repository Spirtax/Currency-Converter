import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class newUser {

	private static String newUsernameErrorMessage = "That username already exists, please enter a new one.";
	private static String newPasswordErrorMessage = "That password already exists, please enter a new one.";
	
	
	public static void userSignUp(String username, String password) throws IOException {
	    BufferedWriter writer = new BufferedWriter(new FileWriter(userlogin.UserInfoFile, true));

	    // Use try-with-resources to ensure proper resource management
	    try (Scanner file = new Scanner(new File(userlogin.UserInfoFile))) {
	        while (file.hasNextLine()) {
	            
	        	String[] userinfo = file.nextLine().trim().split("-");

	            // Update this line to use userinfo instead of userlogin.userInfo
	            System.out.println(Arrays.toString(userinfo));

	            // Update conditions to use userinfo instead of userlogin.userInfo
	            if (username.equals(userinfo[0])) {
	                System.out.println(newUsernameErrorMessage);
	                return;  // Use return instead of break to exit the method
	            } else if (password.equals(userinfo[1])) {
	                System.out.println(newPasswordErrorMessage);
	                return;  // Use return instead of break to exit the method
	            }
	            // Remove the third condition as it is redundant
	        }

	        // Move the newline and write operations outside the loop to avoid duplication
	        writer.newLine();

	        // user info is stored into UserInfoFile in the format "username-password"
	        System.out.println(username + "-" + password);
	        writer.write(username + "-" + password);
	        writer.flush();
	    } catch (IOException e) {
	        // Handle the IOException appropriately
	        e.printStackTrace();
	    } finally {
	        // Use try-with-resources to ensure proper resource management
	        try {
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
}
