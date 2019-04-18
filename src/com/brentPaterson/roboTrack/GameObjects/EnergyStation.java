package com.brentPaterson.roboTrack.GameObjects;
import java.util.Random;

import com.brentPaterson.roboTrack.Game;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class EnergyStation extends Fixed {
	private int capacity;
	private boolean used;
	
	public EnergyStation() {
		Random rand = new Random();
		location = new float[2];
		
		size = rand.nextInt(101) + 50; // range between 50 and 100
		location[0] = (float) rand.nextInt((int) Game.getMapResolution()[0] - size) +  (float) size / 2;
		location[1] = (float) rand.nextInt((int) Game.getMapResolution()[1] - size) +  (float) size / 2;
		
		color = ColorUtil.GREEN;
		capacity = size;
		used = false;
	}
	
	public int useStation() {
		color = ColorUtil.rgb(0, 64, 0);
		int tempCapacity = capacity;
		capacity = 0;
		used = true;
		
		return tempCapacity;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public boolean isUsed() {
		return used;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		g.fillArc((int)(pCmpRelPrnt.getX() + this.getLocation()[0]), (int)(pCmpRelPrnt.getY() + this.getLocation()[1]),
				this.getSize(), this.getSize(), 0, 360);
		
	}

	@Override
	public void handleCollision(GameObject otherObject) {
		// handles in robot
		
	}

	@Override
	public void setSelected(boolean yesNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		return false;
	}

}
