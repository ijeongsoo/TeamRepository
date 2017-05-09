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
public class ToggleSwitch extends Parent{
	private BooleanProperty switchedOn = new SimpleBooleanProperty(false);
	
	private TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));
	private FillTransition fillAnimation = new FillTransition(Duration.seconds(0.25));
	
	private ParallelTransition animation = new ParallelTransition(translateAnimation, fillAnimation);
	
	public BooleanProperty switchedOnProperty(){
		return switchedOn;
	}
	public String buttonString(){
		return switchedOn.getValue().toString();
	}
	
	public ToggleSwitch(){
		Rectangle background = new Rectangle(100, 50);
		
		background.setArcWidth(50);
		background.setArcHeight(50);
		background.setFill(Color.WHITE);
		background.setStroke(Color.LIGHTGRAY);
		
		Circle trigger = new Circle(25);
		trigger.setCenterX(25);
		trigger.setCenterY(25);
		trigger.setFill(Color.WHITE);
		trigger.setStroke(Color.LIGHTGRAY);
		
		DropShadow shadow = new DropShadow();
		shadow.setRadius(2);
		trigger.setEffect(shadow);
		
		translateAnimation.setNode(trigger);
		fillAnimation.setShape(background);
		
		getChildren().addAll(background, trigger);
		
		switchedOn.addListener(((observable, oldValue, newValue) -> {
			boolean isOn = newValue.booleanValue();
			translateAnimation.setToX(isOn ? 100 - 50 : 0);
			fillAnimation.setFromValue(isOn ? Color.WHITE : Color.LIGHTGREEN);
			fillAnimation.setToValue(isOn ? Color.LIGHTGREEN : Color.WHITE);
			
			animation.play();
		}));
		
		setOnMouseClicked(e->{
			switchedOn.set(!switchedOn.get());
		});
	}
	
}
