/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.Condition;

import com.tomcatisbabycat.homepanel.samplestatus.SampleStatus;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
	NumberAxis xAxis ;
	NumberAxis yAxis ;

	private String getTime() {
		Calendar ca = Calendar.getInstance();
		String time = String.valueOf(ca.get(Calendar.SECOND));

		return time;
	}

	private double getTemperature() {
		SampleStatus ss = SampleStatus.getInstance();
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
		// TODO
		
		final double MIN = 0;
		final double MAX = 60;
		yAxis=(NumberAxis)chartTemp.getYAxis();
		yAxis.setAutoRanging(true);
		yAxis.setForceZeroInRange(false);
		chartTemp.setAnimated(true);
		chartTemp.setLegendVisible(false);
		xAxis= (NumberAxis)chartTemp.getXAxis();
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
		
		Timeline graphTl = new Timeline();
		graphTl.getKeyFrames().add(new KeyFrame(Duration.millis(1000), (event) -> {
			timeToGrape();
		}));
		graphTl.setCycleCount(Animation.INDEFINITE);
		graphTl.play();
	}	
	
}
