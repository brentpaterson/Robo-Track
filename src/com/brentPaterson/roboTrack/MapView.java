package com.brentPaterson.roboTrack;

import java.util.Observable;
import java.util.Observer;

public class MapView implements Observer {
	
	public MapView(Observable myModel) {
		myModel.addObserver(this);
	}

	public MapView() {
		
	}
	
	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
	}

}
