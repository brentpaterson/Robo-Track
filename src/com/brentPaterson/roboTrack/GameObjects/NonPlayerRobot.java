package com.brentPaterson.roboTrack.GameObjects;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public class NonPlayerRobot extends Robot {
	private int steeringDirection;
	private int maximumSpeed;
	private int energyLevel;
	private int energyConsumptionRate;
	private int damageLevel;
	
	public NonPlayerRobot() {
		size = 40;
		color = ColorUtil.BLUE;
		
		steeringDirection = 0;
		maximumSpeed = 50;
		energyLevel = 50;
		energyConsumptionRate = 5;
		damageLevel = 0;
		
		heading = 0;
		speed = 1;
		
		Random rand = new Random();
		int angle = rand.nextInt(360);
		int distance = rand.nextInt(size*3) + size*3; // anywhere from 120-240 units away
		location[0] = (float) Math.cos(Math.toRadians(angle)) * distance;
		location[1] = (float) Math.sin(Math.toRadians(angle)) * distance;
	}
}
