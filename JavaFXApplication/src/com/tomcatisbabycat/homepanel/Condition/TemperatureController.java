/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.Condition;

import com.tomcatisbabycat.homepanel.controller.knob.knobfx.Knob;
import com.tomcatisbabycat.homepanel.samplestatus.SampleStatus;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ijeongsu
 */
public class TemperatureController implements Initializable {

	@FXML
	private LineChart chartTemp;

	/**
	 * Initializes the controller class.
	 */
	private XYChart.Series<Number, Number> series;
	private static final int MAX_DATA_POINTS = 8;
	private double sequence = 0;
	NumberAxis xAxis;
	NumberAxis yAxis;
	SampleStatus ss = SampleStatus.getInstance();

	static Timeline graphTl = new Timeline();

	@FXML
	private Knob currentTempKnob;
	@FXML
	private Label lblKnobTemp;
	@FXML
	private Knob wishTempKnob;
	@FXML
	private Label lblWishKnobTemp;
	@FXML
	private AnchorPane AnchorTemp;

	private String getTime() {
		Calendar ca = Calendar.getInstance();
		String time = String.valueOf(ca.get(Calendar.SECOND));

		return time;
	}

	private double getTemperature() {
		return ss.getTemperature();
	}

	private void timeToGrape() {
		if (sequence > MAX_DATA_POINTS + 2) {
			series.getData().remove(0);

		}
		series.getData().add(new XYChart.Data<Number, Number>(sequence, getTemperature()));
		sequence++;
		if (sequence > MAX_DATA_POINTS + 1) {
			//xAxis.setLowerBound(xAxis.getLowerBound());
			Timeline tl = new Timeline();
			KeyValue kv = new KeyValue(xAxis.lowerBoundProperty(), xAxis.getLowerBound() + 1);
			KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
			tl.getKeyFrames().add(kf);
			tl.play();
			Timeline tl2 = new Timeline();
			KeyValue kv2 = new KeyValue(xAxis.upperBoundProperty(), xAxis.getUpperBound() + 1);
			KeyFrame kf2 = new KeyFrame(Duration.millis(500), kv2);
			tl2.getKeyFrames().add(kf2);
			tl2.play();
			//xAxis.setUpperBound(xAxis.getUpperBound() +1);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		yAxis = (NumberAxis) chartTemp.getYAxis();
		yAxis.setAutoRanging(true);
		yAxis.setForceZeroInRange(false);
		chartTemp.setAnimated(true);
		chartTemp.setLegendVisible(false);
		xAxis = (NumberAxis) chartTemp.getXAxis();
		xAxis.setAnimated(false);
		xAxis.setLowerBound(0);
		xAxis.setUpperBound(MAX_DATA_POINTS + 1);
		xAxis.setForceZeroInRange(false);
		xAxis.setAutoRanging(false);
		xAxis.setTickUnit(1);
		xAxis.setTickLabelsVisible(false);
		xAxis.setTickMarkVisible(false);
		xAxis.setMinorTickVisible(false);

		series = new XYChart.Series();
		series.setName("Temperature");
		series.getData().add(new XYChart.Data<Number, Number>(sequence++, getTemperature()));

		chartTemp.getData().add(series);

		chartTemp.getStylesheets().add(getClass().getResource("TemperatureChart.css").toString());
		chartTemp.applyCss();

		Thread thread = new Thread(() -> {
			while (AnchorTemp.isVisible()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
				}
				Platform.runLater(() -> {
					timeToGrape();
				timeToTemp();
				});
				
			}
			System.out.println("온도 스레드 종료");

		});
		thread.setDaemon(true);
		thread.start();

		currentTempKnob.setControl(false);
		currentTempKnob.setMarkerColor(Color.rgb(255, 0, 103));
		currentTempKnob.setValue(ss.getTemperature() * 100 / 50);
		lblKnobTemp.setText((int) ss.getTemperature() + "°");
		lblKnobTemp.setTextFill(Color.rgb(255, 0, 103));
		lblKnobTemp.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				String clean = newValue.replaceAll("[^0-9]", "");
				currentTempKnob.setValue(Integer.parseInt(clean) * 100 / 50);
			}
		});

		wishTempKnob.setMarkerColor(Color.rgb(255, 0, 103));
		wishTempKnob.setValue(ss.getWishTemperature() * 100 / 50);
		lblWishKnobTemp.setText((int) ss.getWishTemperature() + "°");
		lblWishKnobTemp.setTextFill(Color.rgb(255, 0, 103));
		wishTempKnob.setOnMouseDragged((event) -> {
			//System.out.println(Math.atan2(225-event.getSceneY(),225-event.getSceneX())*180/Math.PI);
			if ((Math.atan2(wishTempKnob.getHeight() / 2 - event.getY(), wishTempKnob.getWidth() / 2 - event.getX()) * 180 / Math.PI) > 0) {
				wishTempKnob.setValue((Math.atan2(wishTempKnob.getHeight() / 2 - event.getY(), wishTempKnob.getWidth() / 2 - event.getX()) * 180 / Math.PI) * 100 / 180);
			} else if ((Math.atan2(wishTempKnob.getHeight() / 2 - event.getY(), wishTempKnob.getWidth() / 2 - event.getX()) * 180 / Math.PI) > -90) {
				wishTempKnob.setValue(0);
			} else if ((Math.atan2(wishTempKnob.getHeight() / 2 - event.getY(), wishTempKnob.getWidth() / 2 - event.getX()) * 180 / Math.PI) < -90) {
				wishTempKnob.setValue(100);
			}
		});

		wishTempKnob.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				lblWishKnobTemp.setText(newValue.intValue() * 50 / 100 + "°");
				ss.setWishTemperature(newValue.intValue() * 50 / 100);
			};
	});


		
	}	

	private void timeToTemp() {
		lblKnobTemp.setText((int) getTemperature() + "°");
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("온도 제거 ");
	}
	

}
