/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.consume;

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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
public class GasController implements Initializable {

	@FXML
	private LineChart chartDust;

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
	CategoryAxis barXAxis;
	NumberAxis barYAxis;
	@FXML
	private AnchorPane AnchorGas;
	@FXML
	private Label month1;
	@FXML
	private Label month2;
	@FXML
	private Label month3;
	@FXML
	private Label thisMonthUsed;
	
	private CustomBarChart barChartGas;
	List<Month> list = SampleConsume.getInstance().getList();
	Pane p= new Pane();	

	private double getGasUsed() {
		return ss.getGasUsed();

	}

	private void timeToGrape() {
		
		Timeline lblTime = new Timeline();
		KeyValue lkv = new KeyValue(p.layoutXProperty(),list.get(3).getGasTotalUsed());
		KeyFrame lkf = new KeyFrame(Duration.millis(500),lkv);
		lblTime.getKeyFrames().add(lkf);
		lblTime.play();

		series.getData().add(new XYChart.Data<Number, Number>(sequence++, getGasUsed()));
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
		yAxis = (NumberAxis) chartDust.getYAxis();
		yAxis.setAutoRanging(true);
		yAxis.setForceZeroInRange(false);
		chartDust.setAnimated(true);
		chartDust.setLegendVisible(false);
		xAxis = (NumberAxis) chartDust.getXAxis();
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
		series.setName("gas");
		series.getData().add(new XYChart.Data<Number, Number>(sequence++, getGasUsed()));

		chartDust.getData().add(series);
		//series.getNode().lookup(".default-color0.chart-series-line").setStyle("-fx-stroke: rgb(" + 255 + "," + 255 + "," + 255 + "); ");
		chartDust.getStylesheets().add(getClass().getResource("gasChart.css").toString());
		chartDust.applyCss();

		Thread thread = new Thread(() -> {
			while (AnchorGas.isVisible()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
				}
				Platform.runLater(() -> {
					timeToGrape();
				});

			}
			System.out.println("가스 스레드 종료");

		});
		thread.setDaemon(true);
		thread.start();
		
		final CategoryAxis barXAxis = new CategoryAxis();
		final NumberAxis barYAxis = new NumberAxis();
		
		double upper = graphUpper();
		
		barYAxis.setUpperBound(upper);
		barYAxis.setTickUnit(upper/10);
		
		barChartGas=new CustomBarChart(barXAxis, barYAxis, Color.rgb(123, 85, 81));
		
		
		AnchorGas.getChildren().add(barChartGas);
		
		
		
		barChartGas.setVerticalZeroLineVisible(false);
		barChartGas.setHorizontalZeroLineVisible(false);
		
		barChartGas.getStylesheets().add(getClass().getResource("gasBarChart.css").toString());
		barChartGas.applyCss();

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
		KeyValue kv1 =new KeyValue(((XYChart.Data)barSeries.getData().get(0)).YValueProperty(), list.get(0).getGasTotalUsed());
		KeyValue kv2 =new KeyValue(((XYChart.Data)barSeries.getData().get(1)).YValueProperty(), list.get(1).getGasTotalUsed());
		KeyValue kv3 =new KeyValue(((XYChart.Data)barSeries.getData().get(2)).YValueProperty(), list.get(2).getGasTotalUsed());
		
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(2500),kv1, kv2, kv3));
		tl.play();
		

		barChartGas.getData().add(barSeries);
		
		thisMonthUsed.setText(String.valueOf(Math.round(list.get(3).getGasTotalUsed()*1000d)/1000d));
		p.setLayoutX(Math.round(list.get(3).getGasTotalUsed()*1000d)/1000d);
		p.layoutXProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				thisMonthUsed.setText(String.valueOf(Math.round(newValue.doubleValue()*1000d)/1000d));
				
			}
		});

	}
	
	private double graphUpper() {
		double max=list.get(0).getGasTotalUsed();
		for(int i=0; i<list.size()-1; i++){
			if(list.get(i).getGasTotalUsed()>max){
				max=list.get(i).getGasTotalUsed();
			}		
		}
		return max*1.25;
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("가스 제거");
	}
	

}
