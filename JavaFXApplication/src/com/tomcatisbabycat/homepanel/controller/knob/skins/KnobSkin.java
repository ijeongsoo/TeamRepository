package com.tomcatisbabycat.homepanel.controller.knob.skins;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.SkinBase;
import com.tomcatisbabycat.homepanel.controller.knob.knobfx.Knob;
import javafx.scene.paint.Color;

/**
 *
 * @author F-effect
 */
public class KnobSkin extends SkinBase<Knob> {

	private static final double PREFERRED_WIDTH = 200;
	private static final double PREFERRED_HEIGHT = 200;
	private static final double MINIMUM_WIDTH = 50;
	private static final double MINIMUM_HEIGHT = 50;
	private static final double MAXIMUM_WIDTH = 1024;
	private static final double MAXIMUM_HEIGHT = 1024;

	double sinValue;
	double cosValue;
	double center_radiusX;
	double center_radiusY;
	double startAngle;
	double angleStep;
	double centerX;
	double centerY;
	double radius;
	double valueAngle;

	private Canvas canvas;
	private GraphicsContext gc;

	public KnobSkin(Knob analogClock) {
		super(analogClock);
		init();
		initGraphics();
		registerListeners();
	}

	@Override
	public void dispose() {
		getChildren().clear();
		super.dispose();
	}

	private void init() {
		if (Double.compare(getSkinnable().getPrefWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getPrefHeight(), 0.0) <= 0
			  || Double.compare(getSkinnable().getWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getHeight(), 0.0) <= 0) {
			if (getSkinnable().getPrefWidth() < 0 && getSkinnable().getPrefHeight() < 0) {
				getSkinnable().setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
			}
		}
		if (Double.compare(getSkinnable().getMinWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getMinHeight(), 0.0) <= 0) {
			getSkinnable().setMinSize(MINIMUM_WIDTH, MINIMUM_HEIGHT);
		}

		if (Double.compare(getSkinnable().getMaxWidth(), 0.0) <= 0 || Double.compare(getSkinnable().getMaxHeight(), 0.0) <= 0) {
			getSkinnable().setMaxSize(MAXIMUM_WIDTH, MAXIMUM_HEIGHT);
		}
	}

	private void initGraphics() {

		startAngle = getSkinnable().getStartAngle();
		angleStep = getSkinnable().getAngleStep();

		radius = getSkinnable().getPrefWidth() * 0.1;

		centerX = getSkinnable().getPrefWidth() * 0.5;
		centerY = getSkinnable().getPrefHeight() * 0.5;

		canvas = new Canvas(getSkinnable().getPrefHeight() + radius, getSkinnable().getPrefWidth() + radius);
		gc = canvas.getGraphicsContext2D();

		for (double angle = 0, counter = 0; counter <= getSkinnable().getMaxValue(); angle -= angleStep, counter++) {

			sinValue = Math.sin(Math.toRadians(angle + startAngle));
			cosValue = Math.cos(Math.toRadians(angle + startAngle));

			center_radiusX = (centerX * sinValue) + centerX;
			center_radiusY = (centerY * cosValue) + centerY;

			gc.fillOval(center_radiusX, center_radiusY, radius, radius);
			gc.setFill(getSkinnable().getTickMarkColor());
		}

		gc.setFill(getSkinnable().getMarkerColor());
		drawMarkerTickMarks(gc);
		if(getSkinnable().isControl()){
			drawMarkerPoint(gc);
		}
		

		getChildren().addAll(canvas);
	}

	private void registerListeners() {
		getSkinnable().valueProperty().addListener(e -> {
			getChildren().clear();
			initGraphics();
		});
	}

	private void drawMarkerPoint(GraphicsContext gc) {

		startAngle = getSkinnable().getStartAngle();
		valueAngle = startAngle - (getSkinnable().getValue() - getSkinnable().getMinValue()) * getSkinnable().getAngleStep();

		radius = getSkinnable().getPrefWidth() * 0.1;
		double _radius = getSkinnable().getPrefWidth() * 0.1;

		sinValue = Math.sin(Math.toRadians(valueAngle));
		cosValue = Math.cos(Math.toRadians(valueAngle));

		centerX = getSkinnable().getPrefWidth() * 0.5;
		centerY = getSkinnable().getPrefHeight() * 0.5;

		center_radiusX = (centerX * sinValue) + centerX;
		center_radiusY = (centerY * cosValue) + centerY;

		gc.setFill(Color.WHITE);
		gc.fillOval(center_radiusX, center_radiusY, _radius, _radius);
		gc.setStroke(getSkinnable().getMarkerColor());
		gc.strokeOval(center_radiusX, center_radiusY, radius, radius);

	}

	private void drawMarkerTickMarks(GraphicsContext gc) {

		startAngle = getSkinnable().getStartAngle();
		angleStep = getSkinnable().getAngleStep();
		centerX = getSkinnable().getPrefWidth() * 0.5;
		centerY = getSkinnable().getPrefHeight() * 0.5;
		radius = getSkinnable().getPrefWidth() * 0.1;

		for (double angle = 0, counter = 0; counter <= getSkinnable().getValue(); angle -= angleStep, counter++) {

			sinValue = Math.sin(Math.toRadians(angle + startAngle));
			cosValue = Math.cos(Math.toRadians(angle + startAngle));

			center_radiusX = (centerX * sinValue) + centerX;
			center_radiusY = (centerY * cosValue) + centerY;

			gc.fillOval(center_radiusX, center_radiusY, radius, radius);
			gc.setFill(getSkinnable().getMarkerColor());
		}

	}

}
