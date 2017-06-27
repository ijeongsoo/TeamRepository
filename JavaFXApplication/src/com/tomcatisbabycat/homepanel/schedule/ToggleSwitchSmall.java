/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.schedule;

import javafx.scene.paint.Color;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author kang
 */
public class ToggleSwitchSmall extends Parent {

	//private static BooleanProperty switchedOn = new SimpleBooleanProperty(false);
	private Switched switchedOn = new Switched();

	private TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));
	private FillTransition fillAnimation = new FillTransition(Duration.seconds(0.25));

	private ParallelTransition animation = new ParallelTransition(translateAnimation, fillAnimation);

	public BooleanProperty switchedOnProperty() {
		return switchedOn.so;
	}

	public String buttonString() {
		return switchedOn.so.getValue().toString();
	}

	public ToggleSwitchSmall() {
		Rectangle background = new Rectangle(50, 25);

		background.setArcWidth(25);
		background.setArcHeight(25);
		background.setFill(Color.WHITE);
		background.setStroke(Color.LIGHTGRAY);

		Circle trigger = new Circle(12.5);
		trigger.setCenterX(12.5);
		trigger.setCenterY(12.5);
		trigger.setFill(Color.WHITE);
		trigger.setStroke(Color.LIGHTGRAY);

		DropShadow shadow = new DropShadow();
		shadow.setRadius(2);
		trigger.setEffect(shadow);

		translateAnimation.setNode(trigger);
		fillAnimation.setShape(background);

		getChildren().addAll(background, trigger);

		//animate();

		setOnMouseClicked(e -> {
			System.out.println("");
			switchedOn.so.set(!switchedOn.so.get());
			animate();
		});
	}

	public void animate() {
		boolean isOn = switchedOn.so.get();
		translateAnimation.setToX(isOn ? 50 - 25 : 0);
		fillAnimation.setFromValue(isOn ? Color.WHITE : Color.LIGHTGREEN);
		fillAnimation.setToValue(isOn ? Color.LIGHTGREEN : Color.WHITE);

		animation.play();
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("Toggle Small is Out");
	}
}
