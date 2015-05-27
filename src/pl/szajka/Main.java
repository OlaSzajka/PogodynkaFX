package pl.szajka;
	
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import org.json.simple.parser.ParseException;


public class Main extends Application {
	public ObservableList<String> countryList;
	private WeatherClient client;
	public WuConditions condition;
	public WuAstronomy astronomy;
	public WuSateliteMap map;
	public WuHistory history;
	public DataBase db;
	
	public Main() {
		db = new DataBase();
		countryList = db.getCountryNameList();
	
		try {
			 
			client = new WeatherClient();
						
			//System.out.println(client.getDocument("conditions/q/Poland/Krakow.json"));
//			condition = client.getCurrentCondition("Poland", "Krakow");
//			
//			System.out.println("Aktualizajca: " + condition.observation_time);
//			System.out.println("Aktualna temp: " + condition.temp_c + "C");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getWuData(String country, String city) {
		try {
			this.condition = client.getCurrentCondition(country, city);
			this.astronomy = client.getAstronomy(country, city);
			this.map = client.getSatelliteMap(country, city);
			this.history = client.getHistoryData(country, city);

		} catch (WuException  | ParseException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("B³¹d");
			alert.setHeaderText("Problem z pobraniem danych!");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}
	
//	public void getWuHistory(String county, String city, Date)
	
    private BorderPane rootLayout; 
    
	@Override
	public void start(Stage primaryStage) {
		try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("./MainWindow.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setMinHeight(520.0);
            primaryStage.setMinWidth(660.0);
            primaryStage.show();
            
            // Give the controller access to the main app.
            MainWindowController controller = loader.getController();
            controller.setMainApp(this);
            
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
