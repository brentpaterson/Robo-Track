package com.brentPaterson.roboTrack.GameObjects;

import java.util.Vector;

import com.brentPaterson.roboTrack.GameObjects.Interfaces.ICollider;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Base extends Fixed {
	private int sequenceNumber;
	
	public Base(int sequenceNumber, float[] locationInput) {
		size = 100;
		color = ColorUtil.LTGRAY;
		this.sequenceNumber = sequenceNumber;
		location = new float[2];
		location[0] = locationInput[0];
		location[1] = locationInput[1];
		collidingObjects = new Vector<>();
	}
	
	public int getSeqNum() {
		return sequenceNumber;
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
		g.fillPolygon(x, y, 3);
		
		int stringWidth = g.getFont().stringWidth(Integer.toString(sequenceNumber));
		 
		g.setColor(ColorUtil.BLACK);
		g.drawString(Integer.toString(sequenceNumber), 
				(int)(pCmpRelPrnt.getX() + this.getLocation()[0] + 0.5 * this.getSize() - stringWidth / 2),
				(int)(pCmpRelPrnt.getY() + this.getLocation()[1] + 0.5 * this.getSize())); 
	}

	@Override
	public void handleCollision(GameObject otherObject) {
		// nothing happens to base
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
