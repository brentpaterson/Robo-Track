package com.brentPaterson.roboTrack.GameObjects;

import java.util.Random;

import com.brentPaterson.roboTrack.Game;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Drone extends Movable {


	public Drone() {
		Random rand = new Random();
		location = new float[2];
		
		size = rand.nextInt(26) + 50; // range between 50 and 75
		location[0] = (float) rand.nextInt((int) Game.getMapResolution()[0] - size) +  (float) size / 2;
		location[1] = (float) rand.nextInt((int) Game.getMapResolution()[1] - size) +  (float) size / 2;
		
		color = ColorUtil.MAGENTA;
		
		speed = rand.nextInt(6) + 5;
		heading = rand.nextInt(360);
	}
	 
	public void updateHeading() {
		Random rand = new Random();
		heading += (rand.nextInt(11) - 5);
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int[] x = new int[3];
		int[] y = new int[3];
		x[0] = (int)(pCmpRelPrnt.getX() + this.getLocation()[0]);
		x[1] = (int)(pCmpRelPrnt.getX() + this.getLocation()[0] + this.getSize());
		x[2] = (int)(pCmpRelPrnt.getX() + this.getLocation()[0] + 0.5 * this.getSize());
		
		y[0] = (int)(pCmpRelPrnt.getY() + this.getLocation()[1] + this.getSize());
		y[1] = (int)(pCmpRelPrnt.getY() + this.getLocation()[1] + this.getSize());
		y[2] = (int)(pCmpRelPrnt.getY() + this.getLocation()[1]);
		
		g.setColor(this.getColor());
		g.drawPolygon(x, y, 3);
		
	}

}
