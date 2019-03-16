package com.brentPaterson.roboTrack;

import java.util.Observable;
import java.util.Observer;

import com.brentPaterson.roboTrack.GameWorldProxy.GameWorldProxy;
import com.codename1.ui.Container;

public class ScoreView extends Container implements Observer {
	
	public ScoreView(Observable myModel) {
		myModel.addObserver(this);
	}
	
	public ScoreView() {
		
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
