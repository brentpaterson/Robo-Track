package com.brentPaterson.roboTrack.Views;

import java.util.Observable;
import java.util.Observer;

import com.brentPaterson.roboTrack.GameWorldProxy.GameWorldProxy;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

public class ScoreView extends Container implements Observer {
	private Label lives;
	private Label time;
	private Label topBase;
	private Label energyLevel;
	private Label damageLevel;
	private Label soundStatus;
	
	public ScoreView(Observable myModel) {
		myModel.addObserver(this);
		init();
	}
	
	public ScoreView() {
		init();
		
	}

	public void init() {
		System.out.println("Initializing ScoreView");
		this.setLayout(new FlowLayout(CENTER));
		this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLUE));
		lives = new Label("Lives: 3  ");
		time = new Label("Time: 0  ");
		topBase = new Label("Top base: 1  ");
		energyLevel = new Label("Energy Level: 50 ");
		damageLevel = new Label("Damage Level: 0  ");
		soundStatus = new Label("Sound: OFF");
		
		// set padding
		lives.getAllStyles().setPadding(RIGHT, 5);
		time.getAllStyles().setPadding(RIGHT, 5);
		topBase.getAllStyles().setPadding(RIGHT, 5);
		energyLevel.getAllStyles().setPadding(RIGHT, 5);
		damageLevel.getAllStyles().setPadding(RIGHT, 5);		
		
		this.add(lives);
		this.add(time);
		this.add(topBase);
		this.add(energyLevel);
		this.add(damageLevel);
		this.add(soundStatus);
		
		
	}
	
	@Override
	public void update(Observable observable, Object data) {
		// sound status bool -> string
		String soundStatusString;
		if (((GameWorldProxy) observable).getSoundStatus())
			soundStatusString = "ON";
		else
			soundStatusString = "OFF";
		
		// for console
		((GameWorldProxy) observable).consoleDisplay();
		
		// for actual gui
		lives.setText("Lives: " + ((GameWorldProxy) observable).getLives());
		time.setText("Time: " + ((GameWorldProxy) observable).getTime());
		topBase.setText("Top Base: " + ((GameWorldProxy) observable).getTopBase());
		energyLevel.setText("Energy Level: " + ((GameWorldProxy) observable).getPlayerRobot().getEnergyLevel());
		damageLevel.setText("Damage Level: " + ((GameWorldProxy) observable).getPlayerRobot().getDamageLevel());
		soundStatus.setText("Sound: " + soundStatusString);
	}

}
