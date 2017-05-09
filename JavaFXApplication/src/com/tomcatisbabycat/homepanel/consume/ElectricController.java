/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.consume;

import com.tomcatisbabycat.homepanel.samplestatus.SampleStatus;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ijeongsu
 */
public class ElectricController implements Initializable {

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
	private BarChart<?, ?> barChartElec;
	
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
		series.setName("electric");
		series.getData().add(new XYChart.Data<Number, Number>(sequence++, getTemperature()));

		chartTemp.getData().add(series);

		chartTemp.getStylesheets().add(getClass().getResource("electricChart.css").toString());
		chartTemp.applyCss();

		
		graphTl.getKeyFrames().add(new KeyFrame(Duration.millis(1000), (event) -> {
			timeToGrape();
		}));
		graphTl.setCycleCount(Animation.INDEFINITE);
		graphTl.play();
		
		
		barChartElec.getStylesheets().add(getClass().getResource("electricChart.css").toString());
		barChartElec.applyCss();
		XYChart.Series barSeries = new XYChart.Series();
		barSeries.setName("월별 사용량");
		barSeries.setData(FXCollections.observableArrayList(
			  new XYChart.Data("1월", 2000),
			  new XYChart.Data("2월", 1800),
			  new XYChart.Data("3월", 1500),
			  new XYChart.Data("4월", 1800),
			  new XYChart.Data("5월", 2500)
		));
		
		barChartElec.getData().add(barSeries);
	}	
	
}
