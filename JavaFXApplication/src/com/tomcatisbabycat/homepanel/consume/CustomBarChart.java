/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.consume;

import java.util.Locale;
import javafx.geometry.Bounds;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

class CustomBarChart extends BarChart<String, Number> {

	public CustomBarChart(CategoryAxis xAxis, NumberAxis yAxis) {
		super(xAxis, yAxis);

		setAnimated(false);
		setLegendVisible(false);
		setTitleSide(Side.TOP);
		setMinHeight(10);
		setMinWidth(10);
		setBarGap(0);
		setCategoryGap(30);
		setLayoutX(15);
		setLayoutY(199);
		setPrefHeight(206.0);
		setPrefWidth(530);
		setHorizontalZeroLineVisible(false);
		setVerticalZeroLineVisible(false);
		setHorizontalGridLinesVisible(false);
		yAxis.setUpperBound(1000);
		yAxis.setLowerBound(0);
		yAxis.setAutoRanging(false);
		yAxis.setAnimated(false);
		yAxis.setTickUnit(200);
		yAxis.setTickMarkVisible(false);
		yAxis.setTickLabelsVisible(false);
		yAxis.setOpacity(0);
		xAxis.setTickMarkVisible(false);
		xAxis.setOpacity(0);
	}

	

	@Override
	protected void layoutPlotChildren() {
		super.layoutPlotChildren();
		
		// Each individual Data element has its own bar.
		// TODO add label to each bar.
		for (Series<String, Number> series : getData()) {
			for (int i = 0; i < series.getData().size(); i++) {
				StackPane bar = (StackPane) series.getData().get(i).getNode();
				Text text = null;
				for (int j = 0; j < ((Group) bar.getParent()).getChildren().filtered((t) -> {
					return t instanceof Text;
				}).size(); j++) {
					Node node = ((Group) bar.getParent()).getChildren().filtered((t) -> {
						return t instanceof Text;
					}).get(j);
					if (i == j) {
						text = (Text) node;
						break;
					}
				}

				if (text == null) {
					text = new Text(series.getData().get(i).getYValue().intValue() + "");
					Bounds bounds = bar.getBoundsInParent();
					text.setLayoutY(Math.round(
						  bounds.getMinY() - text.prefHeight(-1) * 0.5
					));
					text.setLayoutX(Math.round(
						  bounds.getMinX() + bounds.getWidth() / 2 - text.prefWidth(-1) / 2
					));
					((Group) bar.getParent()).getChildren().add(text);

				} else {
					text.setText(series.getData().get(i).getYValue().intValue() + "");
					Bounds bounds = bar.getBoundsInParent();
					text.setLayoutY(Math.round(
						  bounds.getMinY() - text.prefHeight(-1) * 0.5
					));
					text.setLayoutX(Math.round(
						  bounds.getMinX() + bounds.getWidth() / 2 - text.prefWidth(-1) / 2
					));
				}
			}
		}
	}
}
