/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.consume;

import com.tomcatisbabycat.homepanel.controller.knob.knobfx.Knob;
import com.tomcatisbabycat.homepanel.samplestatus.Month;
import com.tomcatisbabycat.homepanel.samplestatus.SampleConsume;
import com.tomcatisbabycat.homepanel.samplestatus.SampleStatus;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ijeongsu
 */
public class WaterController implements Initializable {

	@FXML
	private LineChart chartMost;

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
	private AnchorPane AnchorWater;
	@FXML
	private Label month1;
	@FXML
	private Label month2;
	@FXML
	private Label month3;
	@FXML
	private Label thisMonthUsed;
	
	private CustomBarChart barChartWater;
	List<Month> list = SampleConsume.getInstance().getList();
	Pane p= new Pane();
	

	private double getWaterUsed() {
		return ss.getWaterUsed();

	}

	private void timeToGrape() {
		
		Timeline lblTime = new Timeline();
		KeyValue lkv = new KeyValue(p.layoutXProperty(),list.get(3).getWaterTotalUsed());
		KeyFrame lkf = new KeyFrame(Duration.millis(500),lkv);
		lblTime.getKeyFrames().add(lkf);
		lblTime.play();

		series.getData().add(new XYChart.Data<Number, Number>(sequence++, getWaterUsed()));
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
		series.setName("water");
		series.getData().add(new XYChart.Data<Number, Number>(sequence++, getWaterUsed()));

		chartMost.getData().add(series);
		chartMost.getStylesheets().add(getClass().getResource("waterChart.css").toString());
		chartMost.applyCss();

		Thread thread = new Thread(() -> {
			while (AnchorWater.isVisible()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
				}
				Platform.runLater(() -> {
					timeToGrape();
				});

			}
			System.out.println("수도 스레드 종료");

		});
		thread.setDaemon(true);
		thread.start();
		
		final CategoryAxis barXAxis = new CategoryAxis();
		final NumberAxis barYAxis = new NumberAxis();
		
		double upper = graphUpper();
		
		barYAxis.setUpperBound(upper);
		barYAxis.setTickUnit(upper/10);
		
		barChartWater=new CustomBarChart(barXAxis, barYAxis, Color.rgb(1, 194, 242));
		
		
		AnchorWater.getChildren().add(barChartWater);
		
		
		
		barChartWater.setVerticalZeroLineVisible(false);
		barChartWater.setHorizontalZeroLineVisible(false);
		
		barChartWater.getStylesheets().add(getClass().getResource("waterBarChart.css").toString());
		barChartWater.applyCss();

		XYChart.Series barSeries = new XYChart.Series();
		barSeries.setName("월별 사용량");
		barSeries.setData(FXCollections.observableArrayList(
			  new XYChart.Data(list.get(0).getMonth(), 0),
			  new XYChart.Data(list.get(1).getMonth(), 0),
			  new XYChart.Data(list.get(2).getMonth(), 0)
		));
		month1.setText(list.get(0).getMonth()+"월");
		month2.setText(list.get(1).getMonth()+"월");
		month3.setText(list.get(2).getMonth()+"월");
	
		Timeline tl = new Timeline();
		KeyValue kv1 =new KeyValue(((XYChart.Data)barSeries.getData().get(0)).YValueProperty(), list.get(0).getWaterTotalUsed());
		KeyValue kv2 =new KeyValue(((XYChart.Data)barSeries.getData().get(1)).YValueProperty(), list.get(1).getWaterTotalUsed());
		KeyValue kv3 =new KeyValue(((XYChart.Data)barSeries.getData().get(2)).YValueProperty(), list.get(2).getWaterTotalUsed());
		
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(2500),kv1, kv2, kv3));
		tl.play();
		

		barChartWater.getData().add(barSeries);
		
		thisMonthUsed.setText(String.valueOf(Math.round(list.get(3).getWaterTotalUsed()*1000d)/1000d));
		p.setLayoutX(Math.round(list.get(3).getWaterTotalUsed()*1000d)/1000d);
		p.layoutXProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				thisMonthUsed.setText(String.valueOf(Math.round(newValue.doubleValue()*1000d)/1000d));
				
			}
		});

	}

	private double graphUpper() {
		double max = list.get(0).getWaterTotalUsed();
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i).getWaterTotalUsed() > max) {
				max = list.get(i).getWaterTotalUsed();
			}
		}
		return max * 1.25;
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("워터 제거 ");
	}

	

}
