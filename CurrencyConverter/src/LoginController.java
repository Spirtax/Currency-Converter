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
public class LoginController implements Initializable {

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
    private Button loginButton;
    @FXML
    private Hyperlink signupLink;
    
    public static String loginUserInput;
    public static String loginPasswordInput;

    // Effects
    private DropShadow shadowEffect;
    private Timeline timelineIn;
    private Timeline timelineOut;
    
    final String main = "/GUI.fxml";
	final String signup = "/Signup.FXML";
	
	@Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            loginPasswordInput = newValue;
        });
        
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
        	loginUserInput = newValue;
        });
		
		
        // Close button
		Image cross = new Image(getClass().getResourceAsStream("cross.png"));
		closeButton.setImage(cross);

		// Create the DropShadow effect
		shadowEffect = new DropShadow(10, Color.rgb(0, 0, 0, 0.5));

		// Change cursor on hover
		closeButton.setOnMouseEntered(this::handleMouseEnter);
		closeButton.setOnMouseExited(this::handleMouseExit);
		
		loginButton.setOnAction(this::handleSignIn);
    }

    public void switchToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(main));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        initializeSceneStyle(root, stage); // Initialize the scene style with rounded edges
        scene = new Scene(root, 400, 600);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
    
	public void switchToSignup(ActionEvent event) throws IOException { 
	    root = FXMLLoader.load(getClass().getResource(signup));
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
    
    //TODO
    //Method for handling when the user presses the sign in button
    //Needs to check if the inputted user/password is correct
    private void handleSignIn(ActionEvent event) {
        // Handle button click event
        if (event.getSource() == loginButton) {
            System.out.println("My button was pressed!");
        }
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

    public void handleCloseButtonClick() {
        System.exit(0);
    } 
}
