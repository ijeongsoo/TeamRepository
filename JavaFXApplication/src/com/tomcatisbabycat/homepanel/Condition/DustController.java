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
public class DustController implements Initializable {

	@FXML
	private LineChart chartDust;

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

	@FXML
	private AnchorPane AnchorDust;
	

	private String getTime() {
		Calendar ca = Calendar.getInstance();
		String time = String.valueOf(ca.get(Calendar.SECOND));

		return time;
	}

	private int getDust() {
		return ss.getDust();

	}

	private void timeToGrape() {

		series.getData().add(new XYChart.Data<Number, Number>(sequence++, getDust()));
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
		series.setName("Dust");
		series.getData().add(new XYChart.Data<Number, Number>(sequence++, getDust()));

		chartDust.getData().add(series);
		chartDust.getStylesheets().add(getClass().getResource("DustChart.css").toString());
		chartDust.applyCss();

		Thread thread = new Thread(() -> {
			while (AnchorDust.isVisible()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {

				}
				Platform.runLater(() -> {
					timeToGrape();
				timeToDust();
				});
				

			}
			System.out.println("먼지쓰레드 종료");

		});
		thread.setDaemon(true);
		thread.start();
		

		currentTempKnob.setControl(false);
		currentTempKnob.setMarkerColor(Color.rgb(167, 113, 20));
		currentTempKnob.setValue(ss.getDust() * 100 / 200);
		lblKnobTemp.setText(ss.getDust() + "㎍/㎥");
		lblKnobTemp.setTextFill(Color.rgb(167, 113, 20));
		lblKnobTemp.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				String clean = newValue.replaceAll("[^0-9]", "");
				currentTempKnob.setValue(Integer.parseInt(clean) * 100 / 200);
			}
		});

		wishTempKnob.setMarkerColor(Color.rgb(167, 113, 20));
		wishTempKnob.setValue(ss.getWishDust());
		lblWishKnobTemp.setText(ss.getWishDust() + "㎍/㎥");
		lblWishKnobTemp.setTextFill(Color.rgb(167, 113, 20));
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

				lblWishKnobTemp.setText(newValue.intValue() * 100 / 100 + "㎍/㎥");
				ss.setWishDust(newValue.intValue());
			}
		;
	}

	);
	}	

	private void timeToDust() {
		lblKnobTemp.setText(getDust() + "㎍/㎥");
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("먼지 제거 ㅇ");
	}

	
}
