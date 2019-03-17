package com.brentPaterson.roboTrack.Views;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	
	public MapView(Observable myModel) {
		myModel.addObserver(this);
		init();
	}

	public MapView() {
		init();
		
	}
	
	public void init() {
		System.out.println("Initializing MapView");
		this.setLayout(new FlowLayout(CENTER));
		this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(255,0,0)));
		
	}
	
	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
	}

}
