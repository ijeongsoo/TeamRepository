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
public class MoistureController implements Initializable {

	@FXML
	private LineChart chartMost;

	private XYChart.Series<Number, Number> series;
	private static final int MAX_DATA_POINTS = 8;
	private double sequence = 0;
	NumberAxis xAxis;
	NumberAxis yAxis;
	SampleStatus ss = SampleStatus.getInstance();
	@FXML
	private Knob currentTempKnob;
	@FXML
	private Knob wishTempKnob;
	@FXML
	private Label lblKnobTemp;
	@FXML
	private Label lblWishKnobTemp;

	static Timeline graphTl = new Timeline();
	@FXML
	private AnchorPane AnchorMoist;

	private String getTime() {
		Calendar ca = Calendar.getInstance();
		String time = String.valueOf(ca.get(Calendar.SECOND));

		return time;
	}

	private double getMoisture() {
		return ss.getMoisture();

	}

	private void timeToGrape() {

		series.getData().add(new XYChart.Data<Number, Number>(sequence++, getMoisture()));
		if (sequence > MAX_DATA_POINTS + 2) {
			series.getData().remove(0);

		}
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

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		yAxis = (NumberAxis) chartMost.getYAxis();
		yAxis.setAutoRanging(true);
		yAxis.setForceZeroInRange(false);
		chartMost.setAnimated(true);
		chartMost.setLegendVisible(false);
		xAxis = (NumberAxis) chartMost.getXAxis();
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
		series.setName("Moisture");
		series.getData().add(new XYChart.Data<Number, Number>(sequence++, getMoisture()));

		chartMost.getData().add(series);
		chartMost.getStylesheets().add(getClass().getResource("MoistureChart.css").toString());
		chartMost.applyCss();

//		
		Thread thread = new Thread(() -> {
			while (AnchorMoist.isVisible()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
				}
				Platform.runLater(() -> {
					timeToGrape();
				timeToMost();
				});
				
			}
			System.out.println("수분 스레드 종료");

		});
		thread.setDaemon(true);
		thread.start();

		currentTempKnob.setControl(false);
		currentTempKnob.setMarkerColor(Color.rgb(1, 194, 242));
		currentTempKnob.setValue(ss.getMoisture());
		lblKnobTemp.setText(ss.getMoisture() + "%");
		lblKnobTemp.setTextFill(Color.rgb(1, 194, 242));
		lblKnobTemp.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				String clean = newValue.replaceAll("[^0-9]", "");
				currentTempKnob.setValue(Integer.parseInt(clean));
			}
		});

		wishTempKnob.setMarkerColor(Color.rgb(1, 194, 242));
		wishTempKnob.setValue(ss.getWishMoisture());
		lblWishKnobTemp.setText(ss.getWishMoisture() + "%");
		lblWishKnobTemp.setTextFill(Color.rgb(1, 194, 242));
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

				lblWishKnobTemp.setText(newValue.intValue() + "%");
				ss.setWishMoisture(newValue.intValue());
			}
		;

	}

	);
	}	

	private void timeToMost() {
		lblKnobTemp.setText((int) getMoisture() + "%");
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("습도 제거 ");
	}
	
	

}
