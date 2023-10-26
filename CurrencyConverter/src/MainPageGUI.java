import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainPageGUI extends Application
{
	
	// create variables for elements of the main page interface 
	private ComboBox<String> fromCurrencyComboBox;
    private ComboBox<String> toCurrencyComboBox;
    private TextField amountTextField;
    private TextField convertedAmountTextField;
    
    public static final String stylesheet = "styles.css";

    // runs the main page GUI
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("Currency Converter");

        // Create UI elements
        Label title = new Label("Currency Converter!");
        title.setStyle("-fx-font-size: 75px; -fx-font-weight: bold;");

        // set the values of the currencies for the user to choose from
        fromCurrencyComboBox = new ComboBox<>();
        fromCurrencyComboBox.setItems(FXCollections.observableArrayList(getCurr()));
        
        
        toCurrencyComboBox = new ComboBox<>();
        toCurrencyComboBox.setItems(FXCollections.observableArrayList(getCurr()));
        
        // set the text prompt for the drop down menus
        fromCurrencyComboBox.setPromptText("From Currency");
        toCurrencyComboBox.setPromptText("To Currency");

        // create area in which the drop boxes will appear / formatting and visuals
        HBox currencySelection = new HBox(fromCurrencyComboBox, new Label("➔"), toCurrencyComboBox);
        currencySelection.setSpacing(10);

        // create area in which the user will input amount wanting to be converted / formatting and visuals
        amountTextField = new TextField();
        convertedAmountTextField = new TextField();
        convertedAmountTextField.setEditable(false);
        
        HBox currencyAmount = new HBox(amountTextField, new Label(" ➔ "), convertedAmountTextField);

        // create and set up button which users will press to initiate the currency conversion
        Button convertButton = new Button("Convert!");
        convertButton.setOnAction(e -> convertCurrency());
        convertButton.setId("convertButton");
        
        // Create the layout
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(title, currencySelection, currencyAmount, convertButton);

        // create scene to place all elements into
        Scene scene = new Scene(root, 750, 500);
        initializeStylesheet(scene);        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void initializeStylesheet(Scene scene) {
        scene.getStylesheets().add(stylesheet);
        getConvertButton(scene).getStyleClass().add("button");
    }
    
    public static Button getConvertButton(Scene scene) {
        return (Button) scene.lookup("#convertButton");
    }
    
    // create a list of currencies in which the users will be able to choose from
    public static ArrayList<String> getCurr() throws FileNotFoundException
    {
    	ArrayList<String> currList = new ArrayList<String>();
    	Scanner file = new Scanner(new File ("Currencies.txt"));
    	
    	while(file.hasNext())
    	{
    		String[] curr = file.nextLine().split(" ");
    		
    		currList.add(curr[curr.length-1]);
    	}
    	
    	Collections.sort(currList);
    	
    	// returns a sorted array list of all currencies
    	return currList;
    }
    
    // abstract method for converting currencies (WIP)
    public void convertCurrency() {};
	
	
	
}
