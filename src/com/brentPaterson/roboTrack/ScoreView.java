package com.brentPaterson.roboTrack;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;

public class ScoreView extends Container implements Observer {
	
	public ScoreView(Observable myModel) {
		myModel.addObserver(this);
	}
	
	public ScoreView() {
		
	}

	@Override
	public void update(Observable observable, Object data) {
		System.out.println("Lives: " + ((GameWorld) observable).getLives());
		System.out.println("Time: " + ((GameWorld) observable).getTime());
		System.out.println("Top base: " + ((GameWorld) observable).getTopBase());
		System.out.println("Current energy level: " + ((GameWorld) observable).getPlayerRobot().getEnergyLevel());
		System.out.println("Current damage level: " + ((GameWorld) observable).getPlayerRobot().getDamageLevel());
	}

}
