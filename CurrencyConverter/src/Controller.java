import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Controller implements Initializable {

    @FXML
    private ComboBox<String> inputComboBox;
    @FXML
    private ComboBox<String> outputComboBox;
    @FXML
    private ImageView closeButton;
    @FXML
    private ImageView convertButton;
    @FXML
    private Pane convertPane;
    @FXML
    private Button favButton;

    // Effects
    private DropShadow shadowEffect;
    private Timeline timelineIn;
    private Timeline timelineOut;

    private Map<String, Boolean> fav = new HashMap<String, Boolean>();
    private ArrayList<String> items;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            // Fill choice boxes
            inputComboBox.getItems().addAll(MainPageGUI.getCurrencies());
            outputComboBox.getItems().addAll(MainPageGUI.getCurrencies());

            // Close button
            Image cross = new Image(getClass().getResourceAsStream("cross.png"));
            closeButton.setImage(cross);
            
            //Convert Button
            Image convert = new Image(getClass().getResourceAsStream("convert.png"));
            convertButton.setImage(convert);

            // Create the DropShadow effect
            shadowEffect = new DropShadow(10, Color.rgb(0, 0, 0, 0.5));

            // Change cursor on hover
            closeButton.setOnMouseEntered(this::handleMouseEnter);
            closeButton.setOnMouseExited(this::handleMouseExit);
            convertPane.setOnMouseEntered(this::handleMouseEnter);
            convertPane.setOnMouseExited(this::handleMouseExit);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
			items = MainPageGUI.getCurrencies();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        for (int i = 0; i < items.size(); ++i) {
        	fav.put(items.get(i), false);
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

    public ComboBox<String> getInputBox() {
        return inputComboBox;
    }

    public ComboBox<String> getOutputBox() {
        return outputComboBox;
    }

    @FXML
    private void handleFavoriteAction(ActionEvent event) {
        String selectedItem = inputComboBox.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
        	if (!fav.get(selectedItem)) {
        		fav.put(selectedItem, true);
        		inputComboBox.getItems().remove(selectedItem); // Remove from the current position
        		inputComboBox.getItems().add(0, selectedItem); // Add at the beginning for favorites
			outputComboBox.getItems().remove(selectedItem); // Remove from the current position
        		outputComboBox.getItems().add(0, selectedItem);
        	}
        	else {
        		fav.put(selectedItem, false);
        		inputComboBox.getItems().remove(selectedItem); // Remove from the current position
        		inputComboBox.getItems().add(items.indexOf(selectedItem), selectedItem); // Add at the beginning for favorites
			outputComboBox.getItems().remove(selectedItem); // Remove from the current position
        		outputComboBox.getItems().add(items.indexOf(selectedItem), selectedItem);
        	}
        }
    }
}
