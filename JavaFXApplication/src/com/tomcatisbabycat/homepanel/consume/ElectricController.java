/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.consume;

import com.tomcatisbabycat.homepanel.samplestatus.SampleStatus;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
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
	private CustomBarChart barChartElec;
	
	@FXML
	private AnchorPane AnchorElec;
	
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

		
		
		Thread thread = new Thread(() -> {
			while (AnchorElec.isVisible()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
				}
				Platform.runLater(() -> {
					timeToGrape();
				});
				
			}
			System.out.println("온도 스레드 종료");

		});
		thread.start();
		
		final CategoryAxis barXAxis = new CategoryAxis();
		barXAxis.setLabel("Bars");
		final NumberAxis barYAxis = new NumberAxis();
		barYAxis.setLabel("Value");
		barChartElec=new CustomBarChart(barXAxis, barYAxis);
		
		
		AnchorElec.getChildren().add(barChartElec);
		
		
		
		barChartElec.setVerticalZeroLineVisible(false);
		barChartElec.setHorizontalZeroLineVisible(false);
		
		//barChartElec.setTitle("ConsumeOfMonth");
		barChartElec.getStylesheets().add(getClass().getResource("electricBarChart.css").toString());
		barChartElec.applyCss();

		XYChart.Series barSeries = new XYChart.Series();
		barSeries.setName("월별 사용량");
		barSeries.setData(FXCollections.observableArrayList(
			  new XYChart.Data("1월", 20),
			  new XYChart.Data("2월", 30),
			  new XYChart.Data("3월", 40),
			  new XYChart.Data("4월", 50),
			  new XYChart.Data("5월", 70)
		));
		Timeline tl = new Timeline();
		KeyValue kv1 =new KeyValue(((XYChart.Data)barSeries.getData().get(0)).YValueProperty(), 700.0);
		KeyValue kv2 =new KeyValue(((XYChart.Data)barSeries.getData().get(1)).YValueProperty(), 300.0);
		KeyValue kv3 =new KeyValue(((XYChart.Data)barSeries.getData().get(2)).YValueProperty(), 100.0);
		KeyValue kv4 =new KeyValue(((XYChart.Data)barSeries.getData().get(3)).YValueProperty(), 800.0);
		KeyValue kv5 =new KeyValue(((XYChart.Data)barSeries.getData().get(4)).YValueProperty(), 500.0);
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(2500),kv1, kv2, kv3, kv4,kv5));
		tl.play();

//		(event) -> {
//				  for (XYChart.Series<String, Number> series : barChartElec.getData()) {
//					  for (XYChart.Data<String, Number> data : series.getData()) {
//						  data.setYValue(Math.random() * 100);
//					  }
//				  }
//			  }
		barChartElec.getData().add(barSeries);
	}	
	
	
	
//	
	
	
}
