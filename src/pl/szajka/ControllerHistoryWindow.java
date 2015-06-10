package pl.szajka;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ControllerHistoryWindow extends Stage {

	@FXML
	private RadioButton radioButtonPressure;

	@FXML
	private Label labelMaxPressure;

	@FXML
	private Label labelMeanTemp;

	@FXML
	private CheckBox checkBoxThunder;

	@FXML
	private Label labelMaxHumidity;

	@FXML
	private Label labelMaxDewpt;

	@FXML
	private CheckBox checkBoxFog;

	@FXML
	private RadioButton radioButtonWind;

	@FXML
	private CheckBox checkBoxSnow;

	@FXML
	private LineChart<Integer, Integer> lineChartHistory;

	@FXML
	private ToggleGroup historyChart;

	@FXML
	private Label labelMeanSnowfall;

	@FXML
	private Label labelMeanSnowdepth;

	@FXML
	private Label labelMinVis;

	@FXML
	private Label labelMaxVis;

	@FXML
	private Label labelMinHumidity;

	@FXML
	private Label labelMeanVis;

	@FXML
	private Label labelMeanPressure;

	@FXML
	private Label labelMeanPrecip;

	@FXML
	private CheckBox checkBoxTornado;

	@FXML
	private RadioButton radioButtonTemp;

	@FXML
	private Label labelMinTemp;

	@FXML
	private CheckBox checkBoxRain;

	@FXML
	private CheckBox checkHail;

	@FXML
	private Label labelMeanHumidity;

	@FXML
	private Label labelMinPressure;

	@FXML
	private Button buttonClose;

	@FXML
	private Label labelMeanDewpt;

	@FXML
	private RadioButton radioButtonPrecip;

	@FXML
	private Label labelMaxTemp;

	@FXML
	private Label labelMinDewpt;

	public ControllerHistoryWindow(Parent parent) {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"HistoryWindow.fxml"));
		fxmlLoader.setController(this);

		// Nice to have this in a load() method instead of constructor, but this
		// seems to be de-facto standard.
		try {
			setScene(new Scene((Parent) fxmlLoader.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateTable(WuDailySummary data) {
		this.labelMeanTemp.setText(data.meantempm + " C");
		this.labelMinTemp.setText(data.mintempm + " C");
		this.labelMaxTemp.setText(data.maxtempm + " C");
		this.labelMeanDewpt.setText(data.meandewptm + " C");
		this.labelMinDewpt.setText(data.mindewptm + " C");
		this.labelMaxDewpt.setText(data.maxdewptm + " C");
		this.labelMeanPressure.setText(data.meanpressurem + " hPa");
		this.labelMinPressure.setText(data.minpressurem + " hPa");
		this.labelMaxPressure.setText(data.maxpressurem + " hPa");
		this.labelMeanVis.setText(data.meanvism + " m");
		this.labelMinVis.setText(data.minvism + " m");
		this.labelMaxVis.setText(data.maxvism + " m");
		this.labelMeanHumidity.setText(data.humidity + " %");
		this.labelMinHumidity.setText(data.minhumidity + " %");
		this.labelMaxHumidity.setText(data.maxhumidity + " %");
		this.labelMeanPrecip.setText(data.precipm + " mm");
		this.labelMeanSnowfall.setText(data.snowdepthm);
		this.labelMeanSnowdepth.setText(data.snowdepthm);

		this.checkBoxFog.setSelected(data.fog);
		this.checkBoxRain.setSelected(data.rain);
		this.checkBoxSnow.setSelected(data.snow);
		this.checkBoxThunder.setSelected(data.thunder);
		this.checkBoxTornado.setSelected(data.tornado);
	}

	public void updateTempChart(List<WuObservation> observations) {
		XYChart.Series series = new XYChart.Series();
		series.setName("Temperatura");

		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		for (WuObservation obs : observations) {
			
			series.getData().add(new XYChart.Data(df.format(obs.date), obs.tempm));
		}
		this.lineChartHistory.getData().add(series);
	}

	@FXML
	public void onButtonCloseAction(ActionEvent event) {
		this.close();
	}
}
