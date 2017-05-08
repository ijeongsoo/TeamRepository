package com.tomcatisbabycat.homepanel.controller.knob.knobfx;

import com.tomcatisbabycat.homepanel.controller.externalFile.CssHelper;
import com.tomcatisbabycat.homepanel.controller.externalFile.DefaultPropertyBasedCssMetaData;
import com.sun.javafx.css.converters.PaintConverter;
import com.tomcatisbabycat.homepanel.controller.knob.skins.KnobSkin;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author F-effect
 */
public class Knob extends Control {

	private final Paint DEFAULT_COLOR_1 = Color.rgb(231, 236, 237);
	private final Paint DEFAULT_COLOR_2 = Color.rgb(41, 130, 213);

	private DoubleProperty maxValue;
	private final double _maxValue;
	private DoubleProperty minValue;
	private final double _minValue;
	private DoubleProperty value;

	private DoubleProperty startAngle;
	private final double _startAngle;
	private final double _angleStep;
	private final double _angleRange;

	private StyleableObjectProperty<Paint> markerColor;
	private StyleableObjectProperty<Paint> tickMarkColor;
	
	private boolean control;

	public Knob() {
		getStyleClass().add("knob");

		_minValue = 0;
		_maxValue = 100;
		_startAngle = 270;
		_angleRange = 180;
		_angleStep = _angleRange / (_maxValue - _minValue);
		control=true;

	}

	@Override
	protected Skin<?> createDefaultSkin() {
		return new KnobSkin(this);
	}

	public final void setValue(double val) {
		if (val <= 0) {
			val = 0;
		} else if (val >= _maxValue) {
			val = _maxValue;
		}
		valueProperty().setValue(val);
	}
	
	public final void setControl(boolean control){
		this.control=control;
	}
	public final boolean isControl(){
		return control;
	}

	public final DoubleProperty valueProperty() {
		if (null == value) {
			value = new SimpleDoubleProperty(Knob.this, "value", 0);
		}
		return value;
	}

	public final double getValue() {
		return null == value ? 0 : value.get();
	}
	
	
	public final double getMaxValue() {
		return null == maxValue ? _maxValue : maxValue.get();
	}

	public final DoubleProperty maxValueProperty() {
		if (null == maxValue) {
			maxValue = new SimpleDoubleProperty(Knob.this, "maxValue", 50);
		}
		return maxValue;
	}

	public double getMinValue() {
		return null == minValue ? _minValue : minValue.get();
	}

	public DoubleProperty minValueProperty() {
		if (null == minValue) {
			minValue = new SimpleDoubleProperty(Knob.this, "minValue", 0);
		}
		return minValue;
	}

	public double getStartAngle() {

		return null == startAngle ? _startAngle : startAngle.get();
	}

	public DoubleProperty startAngleProperty() {
		if (null == startAngle) {
			startAngle = new SimpleDoubleProperty(Knob.this, "startAngle", 320);
		}
		return startAngle;
	}

	public double getAngleStep() {
		return _angleStep;
	}

	public final void setMarkerColor(final Paint color) {
		markerColorProperty().set(color);
	}

	public final Paint getMarkerColor() {
		return null == markerColor ? DEFAULT_COLOR_2 : markerColor.get();
	}

	public final StyleableObjectProperty<Paint> markerColorProperty() {

		if (null == markerColor) {
			markerColor = CssHelper.createProperty(StyleableProperties.MARKER_COLOR, Knob.this);
		}
		return markerColor;
	}
	
	public final void setTickMarkerColor(final Paint color) {
		tickMarkColorProperty().set(color);
	}

	public final Paint getTickMarkColor() {
		return null == tickMarkColor ? DEFAULT_COLOR_1 : tickMarkColor.get();
	}

	public final StyleableObjectProperty<Paint> tickMarkColorProperty() {
		if (null == tickMarkColor) {
			tickMarkColor = CssHelper.createProperty(StyleableProperties.TICKMARK_COLOR, Knob.this);
		}
		return tickMarkColor;
	}

	private static class StyleableProperties {

		private static final DefaultPropertyBasedCssMetaData<Knob, Paint> MARKER_COLOR = CssHelper.createMetaData("-fx-knob-marker-color", PaintConverter.getInstance(), "markerColor", Color.AQUA);

		private static final DefaultPropertyBasedCssMetaData<Knob, Paint> TICKMARK_COLOR = CssHelper.createMetaData("-fx-knob-tick-mark-color", PaintConverter.getInstance(), "tickMarkColor", Color.BLACK);

		private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES = CssHelper.createCssMetaDataList(Control.getClassCssMetaData(), MARKER_COLOR,
			  TICKMARK_COLOR);
	}

	@Override
	public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
		return StyleableProperties.STYLEABLES;
	}

	public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
		return StyleableProperties.STYLEABLES;
	}
}
