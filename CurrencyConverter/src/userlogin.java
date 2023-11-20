import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class userlogin {

    public static final String UserInfoFile = "UserInfo.txt";
    private static final String loginErrorMessage = "The user name or password you entered may be incorrect, please try again.";

    private static final Logger logger = Logger.getLogger(userlogin.class.getName());

    public static boolean validUser(String username, String password) {
        try (Scanner file = new Scanner(new File(UserInfoFile))) {
            while (file.hasNextLine()) {
                String[] userInfo = file.nextLine().trim().split("-");
                if (username.equals(userInfo[0]) && password.equals(userInfo[1])) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            // Handle file not found exception
            logger.log(Level.SEVERE, "User info file not found: " + e.getMessage(), e);
        }

        // If given username and password are invalid, log the error message
        logger.warning(loginErrorMessage);
        return false;
    }
}
