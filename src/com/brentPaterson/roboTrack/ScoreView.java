package com.brentPaterson.roboTrack;

import java.util.Observable;
import java.util.Observer;

public class ScoreView implements Observer {
	
	public ScoreView(Observable myModel) {
		myModel.addObserver(this);
	}
	
	public ScoreView() {
		
	}

	@Override
	public void update(Observable observable, Object data) {
		System.out.println("Lives: " + ((GameWorld) data).getLives());
		System.out.println("Time: " + ((GameWorld) data).getTime());
		System.out.println("Top base: " + ((GameWorld) data).getTopBase());
		System.out.println("Current energy level: " + ((GameWorld) data).getPlayerRobot().getEnergyLevel());
		System.out.println("Current damage level: " + ((GameWorld) data).getPlayerRobot().getDamageLevel());
		
	}

}
