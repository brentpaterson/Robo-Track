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
		lives = new Label("Lives: 3");
		time = new Label("Time: 0");
		topBase = new Label("Top base: 1");
		energyLevel = new Label("Energy Level: 50");
		damageLevel = new Label("Damage Level: 0");
		this.add(lives);
		this.add(time);
		this.add(topBase);
		this.add(energyLevel);
		this.add(damageLevel);
		
		
	}
	
	@Override
	public void update(Observable observable, Object data) {
		System.out.println("Lives: " + ((GameWorldProxy) observable).getLives());
		System.out.println("Time: " + ((GameWorldProxy) observable).getTime());
		System.out.println("Top base: " + ((GameWorldProxy) observable).getTopBase());
		System.out.println("Current energy level: " + ((GameWorldProxy) observable).getPlayerRobot().getEnergyLevel());
		System.out.println("Current damage level: " + ((GameWorldProxy) observable).getPlayerRobot().getDamageLevel());
	}

}
