package com.brentPaterson.roboTrack.GameObjects;

import com.codename1.charts.util.ColorUtil;

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

}
