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
import model.Train;

public class DBOverviewController {
	/**
	 * Overview elements.
	 */
	@FXML
    private Button backButton;
	
	@FXML
	private TableView<Plane> dbTable;
	
	@FXML
	private TableColumn<Plane, String> destinationColumn;
	
	@FXML
	private TableColumn<Plane, Double> distanceColumn;
	
	@FXML
	private TableColumn<Plane, Double> costColumn;
	
	@FXML
	private TableView<Train> trainTable;
	
	@FXML
	private TableColumn<Train, String> trainDestColumn;
	
	@FXML
	private TableColumn<Train, Double> trainDistColumn;
	
	@FXML
	private TableColumn<Train, Double> trainCostColumn;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public DBOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    		//Clear possible previous values before inserting new ones
    		dbTable.getItems().clear();
    		trainTable.getItems().clear();
    		
    		//Populate flights table
    		destinationColumn.setCellValueFactory(cellData -> cellData.getValue().getDestination());
    		distanceColumn.setCellValueFactory(cellData -> cellData.getValue().getDistance().asObject());
    		costColumn.setCellValueFactory(cellData -> cellData.getValue().getTicketCost().asObject());
    		
    		//Populate trains table
    		trainDestColumn.setCellValueFactory(cellData -> cellData.getValue().getDestination());
    		trainDistColumn.setCellValueFactory(cellData -> cellData.getValue().getDistance().asObject());
    		trainCostColumn.setCellValueFactory(cellData -> cellData.getValue().getTicketCost().asObject());
    		
    		//Get observable lists for data
    		dbTable.setItems(MainApp.getPlaneData());
    		trainTable.setItems(MainApp.getTrainData());
    }
    
    /**
     * Method to handle button events to change scenes.
     * @param action the action performed
     */
    @FXML
    private void handleButtonAction(ActionEvent action) throws IOException {
    		FXMLLoader loader = new FXMLLoader();
    		Stage currentStage;
    		Parent root;
    		//Reference to button's stage
    		currentStage = (Stage) backButton.getScene().getWindow();
    			
    		//Load document to switch to
    		root = loader.load(getClass().getResource("TicketerOverview.fxml"));
    			
    		//Create new scene with root and stage
        	Scene scene = new Scene(root);
        	currentStage.setScene(scene);
        	currentStage.show();
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
