package com.brentPaterson.roboTrack.Views;

import java.util.Observable;
import java.util.Observer;

import com.brentPaterson.roboTrack.GameWorld;
import com.brentPaterson.roboTrack.Collection.IIterator;
import com.brentPaterson.roboTrack.GameObjects.GameObject;
import com.brentPaterson.roboTrack.GameWorldProxy.GameWorldProxy;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	private GameWorld gw;
	
	public MapView(Observable myModel) {
		myModel.addObserver(this);
		gw = (GameWorld) myModel;
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
		((GameWorldProxy) observable).displayConsoleMap();
		this.repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Point pCmpRelPrnt = new Point(getX(), getY());
		

		IIterator itr = gw.getIterator();
		while (itr.hasNext()) {
			((GameObject) itr.getNext()).draw(g, pCmpRelPrnt);
			
		}
	}
}
