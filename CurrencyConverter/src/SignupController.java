import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
public class SignupController implements Initializable {

    @FXML
    private ImageView closeButton;
	
    private Stage stage;
    private Scene scene;
    private Parent root;
    private double xOffset = 0;
    private double yOffset = 0;
    
    //Login page
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button signupButton;
    @FXML
    private Hyperlink loginLink;
    
    public static String password;
    public static String username;

    
    // Effects
    private DropShadow shadowEffect;
    private Timeline timelineIn;
    private Timeline timelineOut;
    
	final String login = "/Login.FXML";
	final String main = "/GUI.fxml";
	
	@Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            this.password = newValue;
            System.out.println(password);
        });
        
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            this.username = newValue;
            System.out.println(username);
        });
		
        // Close button
		Image cross = new Image(getClass().getResourceAsStream("cross.png"));
		closeButton.setImage(cross);

		// Create the DropShadow effect
		shadowEffect = new DropShadow(10, Color.rgb(0, 0, 0, 0.5));

		// Change cursor on hover
		closeButton.setOnMouseEntered(this::handleMouseEnter);
		closeButton.setOnMouseExited(this::handleMouseExit);
		
		signupButton.setOnAction(this::handleCreateAccount);
    }

	

    public void switchToLogin(ActionEvent event) throws IOException {
	    
    	root = FXMLLoader.load(getClass().getResource(login));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 400, 600);
        initializeSceneStyle(root, stage); // Initialize the scene style with rounded edges
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
    
	public void switchToMain(ActionEvent event) throws IOException { 
	    
		root = FXMLLoader.load(getClass().getResource(main));
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    initializeSceneStyle(root, stage); // Initialize the scene style with rounded edges
        scene = new Scene(root, 400, 600);
        scene.setFill(Color.TRANSPARENT);
	    stage.setScene(scene);
	    stage.show();
	}

    private void initializeSceneStyle(Parent root, Stage primaryStage) {
        // Make the window draggable
        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent event) -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        // Make window rounded
        Rectangle rect = new Rectangle(400, 600);
        rect.setArcWidth(20);
        rect.setArcHeight(20);
        root.setClip(rect);
    }
    
    // Event handler for mouse enter
    private void handleMouseEnter(MouseEvent event) {
        closeButton.getScene().setCursor(Cursor.HAND);
        animateShadowEffect(0.7);
    }

    // Event handler for mouse exit
    private void handleMouseExit(MouseEvent event) {
        closeButton.getScene().setCursor(Cursor.DEFAULT);
        animateShadowEffect(0);
    }

    // Animate shadow effect
    private void animateShadowEffect(double targetOpacity) {
        if (timelineIn != null) {
            timelineIn.stop();
        }
        if (timelineOut != null) {
            timelineOut.stop();
        }
        timelineIn = new Timeline();
        KeyFrame keyFrameIn = new KeyFrame(Duration.millis(220),
                new KeyValue(shadowEffect.colorProperty(), Color.rgb(0, 0, 0, targetOpacity)));
        timelineIn.getKeyFrames().add(keyFrameIn);
        timelineIn.play();
        closeButton.setEffect(shadowEffect);
    }
    
    //TODO
    //Method for checking whenever user clicks create account button
    //Same as login but adds the newly created user to list of users and checks if they already exist
    private void handleCreateAccount(ActionEvent event) {
        // Handle button click event
        if (event.getSource() == signupButton) {
            try {
                // Call the userSignUp method with the entered username and password
                newUser.userSignUp(usernameField.getText(), passwordField.getText());

                // After successfully signing up, switch to the main page
                switchToMain(event);
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately (log or show an error message)
            }
        }
    }

    public void handleCloseButtonClick() {
        System.exit(0);
    }    
}
