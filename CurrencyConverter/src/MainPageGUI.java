import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainPageGUI extends Application
{
	
	// create variables for elements of the main page interface 
    private double xOffset = 0;
    private double yOffset = 0;
    
    //Scene and its components
    private Scene scene;
    private Parent root;
    
    //Styling sheet
    public static final String stylesheet = "styles.css";
    
    //Currencies text file
    public static final String currencies = "CurrencyConverter/Currencies.txt";
    
    //Font link
    public static final String font1 = "Roboto-Black.ttf";

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("Currency Converter");
        primaryStage.initStyle(StageStyle.TRANSPARENT); 
                
		try {
			//Loads the FXML file, make sure you have JavaFX downloaded in the plugin store
			root = FXMLLoader.load(getClass().getResource("/Login.FXML"));
	        
	        //Make window rounded
	        Rectangle rect = new Rectangle(400, 600);
	        rect.setArcWidth(20);
	        rect.setArcHeight(20);
	        root.setClip(rect);
	        
	        // Make the window draggable
	        root.setOnMousePressed((MouseEvent event) -> {
	            xOffset = event.getSceneX();
	            yOffset = event.getSceneY();
	        });
	        root.setOnMouseDragged((MouseEvent event) -> {
	            primaryStage.setX(event.getScreenX() - xOffset);
	            primaryStage.setY(event.getScreenY() - yOffset);
	        });
	        
	        // create scene to place all elements into
	        scene = new Scene(root, 400, 600);
	        
	        //Initialize the stylesheet
	        initializeStylesheet();        
	        primaryStage.setScene(scene);
	        primaryStage.show(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    private void initializeStylesheet() {
        scene.getStylesheets().add(stylesheet);
        scene.setFill(Color.TRANSPARENT);
    }
    
    // create a list of currencies in which the users will be able to choose from
    static ArrayList<String> getCurrencies() throws FileNotFoundException
    {
    	ArrayList<String> currList = new ArrayList<String>();
    	Scanner file = new Scanner(new File (currencies));
    	while(file.hasNext())
    	{
	    	try {
	    		String[] curr = file.nextLine().split(" ");
	    		currList.add(curr[curr.length-1]);
	    		Collections.sort(currList);
	    	
	    	} catch (Exception e) {}
    	}

    	// returns a sorted array list of all currencies
    	return currList;
    }
    
    public Scene getScene() {
    	return scene;
    }
}
