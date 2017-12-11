package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ticketer.MainApp;
import model.Plane;

public class RateOverviewController {
	/**
	 * Overview elements.
	 */
	@FXML
    private Button applyButton;
    
    @FXML
    private Button backButton;
    
    @FXML
    private TextField newRate;
    
    @FXML
    private ChoiceBox transport;
    
    @FXML
    private Label feedback;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public RateOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    		//Populate choicebox
    		transport.getItems().add("Plane");
    		transport.getItems().add("Train");
    }
    
    /**
     * Method to handle button events to change scenes.
     * @param action the action performed
     */
    @FXML
    private void handleButtonAction(ActionEvent action) throws IOException {
    		FXMLLoader loader = new FXMLLoader();
    		Stage currentStage = (Stage) applyButton.getScene().getWindow();
    		Parent root = loader.load(getClass().getResource("RateOverview.fxml"));
    		if (action.getSource() == backButton) {
    			//Reference to button's stage
    			currentStage = (Stage) backButton.getScene().getWindow();
    			
    			//Load document to switch to
    			root = loader.load(getClass().getResource("TicketerOverview.fxml"));
    			
    			//Create new scene with root and stage
        		Scene scene = new Scene(root);
        		currentStage.setScene(scene);
        		currentStage.show();
    		} else if (action.getSource() == applyButton) {
    			//Check for invalid input or empty fields
    			if (newRate.getText() != null) {
    				try {
    					//Check for valid prices, change rate on all corresponding objects
    					double rate = Double.parseDouble(newRate.getText());
    					if (rate <= 0) {
    						feedback.setText("Not a valid price.");
    					} else {
    						String choiceValue = (String) transport.getValue();
    						if (choiceValue != null && choiceValue.equals("Plane")) {
    							MainApp.pchangeRates(rate);
    							feedback.setText("Applied!");
    						} else if (choiceValue != null && choiceValue.equals("Train")) {
    							MainApp.tchangeRates(rate);
    							feedback.setText("Applied!");
    						} else {
    							feedback.setText("Something went wrong.");
    						}
    					}
    				} catch (NumberFormatException n) {
    					feedback.setText("Not a valid price.");
    				}
    			} else {
    				feedback.setText("Field is empty.");
    			}
    		}
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
