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
 
	private ComboBox<String> fromCurrencyComboBox;
    private ComboBox<String> toCurrencyComboBox;
    private TextField amountTextField;
    private TextField convertedAmountTextField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("Currency Converter");

        // Create UI elements
        Label title = new Label("Currency Converter!");
        title.setStyle("-fx-font-size: 75px; -fx-font-weight: bold;");

        // create and set the values of the currencies for the user to choose from
        fromCurrencyComboBox = new ComboBox<>();
        fromCurrencyComboBox.setItems(FXCollections.observableArrayList(getCurr()));
        
        toCurrencyComboBox = new ComboBox<>();
        toCurrencyComboBox.setItems(FXCollections.observableArrayList(getCurr()));
        
        fromCurrencyComboBox.setPromptText("From Currency");
        toCurrencyComboBox.setPromptText("To Currency");

        HBox currencySelection = new HBox(fromCurrencyComboBox, new Label("➔"), toCurrencyComboBox);
        currencySelection.setSpacing(10);

        amountTextField = new TextField();
        convertedAmountTextField = new TextField();
        convertedAmountTextField.setEditable(false);
        
        HBox currencyAmount = new HBox(amountTextField, new Label(" ➔ "), convertedAmountTextField);

        Button convertButton = new Button("Convert!");
        convertButton.setOnAction(e -> convertCurrency());

        // Create the layout
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(title, currencySelection, currencyAmount, convertButton);

        Scene scene = new Scene(root, 1000, 750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
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
    
    public void convertCurrency() {};
	
	
	
}
