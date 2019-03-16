package com.brentPaterson.roboTrack.GameObjects;

import com.codename1.charts.util.ColorUtil;

public class Base extends Fixed {
	private int sequenceNumber;
	
	public Base(int sequenceNumber, float[] location) {
		size = 10;
		color = ColorUtil.LTGRAY;
		this.sequenceNumber = sequenceNumber;
		this.location = location;
	}
	
	public int getSeqNum() {
		return sequenceNumber;
	}

}
