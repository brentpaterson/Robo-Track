package com.brentPaterson.roboTrack.GameObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Base extends Fixed {
	private int sequenceNumber;
	
	public Base(int sequenceNumber, float[] locationInput) {
		size = 10;
		color = ColorUtil.LTGRAY;
		this.sequenceNumber = sequenceNumber;
		location = new float[2];
		location[0] = locationInput[0];
		location[1] = locationInput[1];
	}
	
	public int getSeqNum() {
		return sequenceNumber;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int[] x = new int[3];
		int[] y = new int[3];
		x[0] = (int)(pCmpRelPrnt.getX() + this.getLocation()[0]);
		x[1] = (int)(pCmpRelPrnt.getX() + this.getLocation()[0] + size);
		x[2] = (int)(pCmpRelPrnt.getX() + this.getLocation()[0] + 0.5 * size);
		
		y[0] = (int)(pCmpRelPrnt.getX() + this.getLocation()[1] + size);
		y[0] = (int)(pCmpRelPrnt.getX() + this.getLocation()[1] + size);
		y[0] = (int)(pCmpRelPrnt.getX() + this.getLocation()[1]);
		
		g.setColor(color);
		g.fillPolygon(x, y, 3);
		g.drawPolygon(x, y, 3); // draw triangle
		
		
		
	}

}
