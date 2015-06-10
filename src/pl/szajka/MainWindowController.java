package pl.szajka;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MainWindowController {
	@FXML
	private Label labelCurrentTemp;
	@FXML
	private Label labelLastUpdate;
	@FXML
	private Label labelFeelslike;
	@FXML
	private Label labelWeather;
	@FXML
	private Label labelHumidity;
	@FXML
	private Label labelPresure;
	@FXML
	private Label labelPrecip;
	@FXML
	private ComboBox<String> comboboxCountry;
	@FXML
	private TextField textFieldCity;
	@FXML
	private ImageView imageIcon;

	// / Warunki
	@FXML
	private Label labelTemperatureString;
	@FXML
	private Label labelHumidity2;
	@FXML
	private Label labelWindDir;
	@FXML
	private Label labelWindKph;
	@FXML
	private Label labelWindGustKph;
	@FXML
	private Label labelDewpoint;
	@FXML
	private Label labelVisibility;
	@FXML
	private Label labelPressure2;
	@FXML
	private Label labelPressureTrend;

	// / Lokalizacja
	@FXML
	private Label labelLatitude;
	@FXML
	private Label labelLongitude;
	@FXML
	private Label labelElevation;

	// / Astronomia
	@FXML
	private Label labelSunrise;
	@FXML
	private Label labelSunset;
	@FXML
	private Label labelDayDuration;
	@FXML
	private Label labelMoonPhase;
	@FXML
	private Label labelPercentIlluminated;
	@FXML
	private Label labelAgeOfMoon;

	// / Map
	@FXML
	private Pane paneMap;
	@FXML
	private ImageView imageViewMap;
	
	// / History
	@FXML
	private DatePicker datePickerHistory;

	// Reference to the main application.
	private Main mainApp;

	@FXML
	private void initalize() {

	}

	@FXML
	private void showCurrentCondiotion(WuConditions conditions) {
		this.labelCurrentTemp.setText(Integer.toString(conditions.temp_c));
		this.labelLastUpdate.setText(conditions.observation_time);
		this.labelFeelslike.setText(conditions.feelslike_c);
		this.labelWeather.setText(conditions.weather);
		this.labelHumidity.setText(conditions.relative_humidity);
		this.labelPresure.setText(conditions.pressure_mb);
		this.labelPrecip.setText(conditions.precip_today_metric);
		this.imageIcon.setImage(new Image(conditions.icon_url));

	}

	private void showCondition(WuConditions conditions) {
		this.labelTemperatureString.setText(conditions.temperature_string);
		this.labelHumidity2.setText(conditions.relative_humidity);
		this.labelWindDir.setText(conditions.wind_dir);
		this.labelWindKph.setText(Integer.toString(conditions.wind_kph));
		this.labelWindGustKph.setText(Integer
				.toString(conditions.wind_gust_kph));
		this.labelDewpoint.setText(Integer.toString(conditions.dewpoint_c));
		this.labelVisibility.setText(conditions.visibility_km);
		this.labelPressure2.setText(conditions.pressure_mb);
		this.labelPressureTrend.setText(conditions.pressure_trend);
	}

	private void showLocation(WuObservationLocation location) {
		this.labelLatitude.setText(location.latitude);
		this.labelLongitude.setText(location.longitude);
		this.labelElevation.setText(Float.toString(location.elevation_m));
	}

	private void showAstronomy(WuAstronomy astro) {
		this.labelSunrise.setText(astro.sunrise_str);
		this.labelSunset.setText(astro.sunset_str);
		this.labelDayDuration.setText(astro.day_duration);
		this.labelMoonPhase.setText(astro.phaseofMoon);
		this.labelPercentIlluminated.setText(astro.percentIlluminated + "%");
		this.labelAgeOfMoon.setText(astro.ageOfMoon);
	}

	private void showDetailsConditions(WuConditions conditions,
			WuAstronomy astro) {
		this.showCondition(conditions);
		this.showLocation(conditions.location);
		this.showAstronomy(astro);
	}

	private void showSatelliteMap(WuSateliteMap map) {
		this.imageViewMap.fitWidthProperty().bind(paneMap.widthProperty());
		this.imageViewMap.fitHeightProperty().bind(paneMap.heightProperty());
		this.imageViewMap.setImage(new Image(map.getImage(
				(int) imageViewMap.getFitWidth(),
				(int) imageViewMap.getFitHeight())));

		System.out.println((int) imageViewMap.getFitWidth());
		System.out.println((int) imageViewMap.getFitHeight());
		System.out.println(map.getImage((int) imageViewMap.getFitWidth(),
				(int) imageViewMap.getFitHeight()));
	}

	@FXML
	private void handleActualize() {
		String country = this.comboboxCountry.getValue();
		String city = this.textFieldCity.getText();
		this.mainApp.getWuData(country, city);
		this.showCurrentCondiotion(mainApp.condition);
		this.showDetailsConditions(mainApp.condition, mainApp.astronomy);
		this.showSatelliteMap(mainApp.map);
		System.out.println("Aktualizajca: "
				+ mainApp.condition.observation_time);
		System.out.println("Kraj: " + country + " Miasto:" + city);
		System.out.println(mainApp.condition.icon_url);
	}

	@FXML
	void onButtonShowHistoryAction(ActionEvent event) {
		
		String date = this.datePickerHistory.getValue().toString();
		String country = this.comboboxCountry.getValue();
		String city = this.textFieldCity.getText();
		this.mainApp.getWuHistory(country, city, date);
		ControllerHistoryWindow historyDialog = new ControllerHistoryWindow(null);
		historyDialog.setTitle(city + " pogoda z dnia: " + date);
		historyDialog.showAndWait();
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
		// Add observable list data to the comboboxCountry
		this.comboboxCountry.setItems(mainApp.countryList);
		this.comboboxCountry.getSelectionModel().select("Poland");

		this.handleActualize();
	}
}
