package ticketer;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.security.InvalidParameterException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.collections.*;
import view.DBOverviewController;
import view.TicketerOverviewController;

import java.util.ArrayList;
import java.util.Scanner;
import model.Plane;
import model.Train;

public class MainApp extends Application {
	/**
	 * Database destination file. Used in program.
	 */
	private static final String DESTINATION = "/PlaneDatabase.txt";
	
	/**
	 * Database distance file. Used in program.
	 */
	private static final String DISTANCE = "/PlaneDistances.txt";
	
	/**
	 * Overview elements.
	 */
    private Stage primaryStage;
    private BorderPane rootLayout;
    private static double pbaseCost = 0.2;
    private static double tbaseCost = 0.5;
    private static ArrayList<String> pdestinations;
    private static ArrayList<Integer> pdistances;
    private static Plane[] flights;
    private static Train[] trains;
    private static ObservableList<Plane> planeData = FXCollections.observableArrayList();
    private static ObservableList<Train> trainData = FXCollections.observableArrayList();
    
    public MainApp() {
		refresh();
    }
    
    /**
     * Refreshes/populates list of destinations and distances for plane.
     */
    public static void refresh() {
	    	try {
	    		pdestinations = new ArrayList<>();
	    		pdistances = new ArrayList<>();
	    		String dbPath = new File("").getAbsolutePath().concat(DESTINATION);
	    		String distPath = new File("").getAbsolutePath().concat(DISTANCE);
	    		
			//Import destinations from file
	        dbPath = new URI(dbPath).getPath();
	        File dbFile = new File(dbPath);
	        Scanner dbScanner = new Scanner(dbFile, "UTF-8");
	        //Populate destinations ArrayList with list of destinations from database
	        while (dbScanner.hasNextLine()) {
	    			pdestinations.add(dbScanner.nextLine());
	        }
	            
	        //Import distances from file
	        distPath = new URI(distPath).getPath();
	        File distFile = new File(distPath);
	        dbScanner = new Scanner(distFile, "UTF-8");
	        while (dbScanner.hasNextLine()) {
				pdistances.add(Integer.parseInt(dbScanner.nextLine()));
	        }
	        dbScanner.close();
	    } catch (Exception e) {
	    		System.out.println("" + e);
	    }
	    	
	    	//Create objects for each destination
	    	flights = new Plane[pdestinations.size()];
	    	trains = new Train[pdestinations.size()];
		for (int i = 0; i < pdestinations.size(); i++) {
			flights[i] = new Plane(pdestinations.get(i), pbaseCost, pdistances.get(i));
			trains[i] = new Train(pdestinations.get(i), tbaseCost, pdistances.get(i));
		}
		
		//Populate observable lists for DBOverview
		for (int i = 0; i < flights.length; i++) {
			planeData.add(flights[i]);
			trainData.add(trains[i]);
		}
    }
    
    /**
     * Change rates for all plane objects.
     * @param newCost the new rate for all flights
     */
    public static void pchangeRates(final double newCost) {
    		//Reassign variable such that refresh uses new rate
    		pbaseCost = newCost;
    		if (flights == null) {
    			refresh();
    		}
    		for (int i = 0; i < flights.length; i++) {
    			flights[i].setRate(newCost);
    		}
    }
    
    /**
     * Change rates for all train objects.
     * @param newCost new rate for all trains
     */
    public static void tchangeRates(final double newCost) {
    		//Reassign variable such that refresh uses new rate
    		tbaseCost = newCost;
    		if(trains == null) {
    			refresh();
    		}
    		for (int i = 0; i < flights.length; i++) {
    			trains[i].setRate(newCost);
    		}
    }
    
    /**
     * Get list of possible destinations for both plane and trains.
     * @return list of destinations
     */
    public static ArrayList<String> getDestinations() {
    		return pdestinations;
    }
    
    /**
     * Get list of plane objects.
     * @return list of plane objects
     */
    public static ObservableList<Plane> getPlaneData() {
    		return planeData;
    }
    
    /**
     * Get list of train objects.
     * @return list of train objects
     */
    public static ObservableList<Train> getTrainData() {
    		return trainData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("TransportApp");
        
        initRootLayout();
        showTicketerOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the ticketer overview inside the root layout.
     */
    public void showTicketerOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/TicketerOverview.fxml"));
            AnchorPane TicketerOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(TicketerOverview);
            
            // Give the controller access to the main app.
            TicketerOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}