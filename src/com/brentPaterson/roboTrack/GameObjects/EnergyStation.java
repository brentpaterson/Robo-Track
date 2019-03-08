package com.brentPaterson.roboTrack.GameObjects;
import java.util.Random;

import com.brentPaterson.roboTrack.Game;
import com.codename1.charts.util.ColorUtil;

public class EnergyStation extends Fixed {
	private int capacity;
	
	public EnergyStation() {
		Random rand = new Random();
		location = new float[2];
		
		size = rand.nextInt(41) + 10; // range between 10 and 50
		location[0] = (float) rand.nextInt((int) Game.rangeX - size) +  (float) size / 2;
		location[1] = (float) rand.nextInt((int) Game.rangeY - size) +  (float) size / 2;
		
		color = ColorUtil.GREEN;
		capacity = size;
	}

}
