package com.brentPaterson.roboTrack.GameObjects;

import com.brentPaterson.roboTrack.GameObjects.Interfaces.ISteerable;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Robot extends Movable implements ISteerable {
	private int steeringDirection;
	private int maximumSpeed;
	private int energyLevel;
	private int energyConsumptionRate;
	private int damageLevel;
	private int lastBaseReached;

	
	public Robot(float[] initialLocation) {
		location = new float[2];
		location[0] = initialLocation[0];
		location[1] = initialLocation[1];
		
		size = 40;
		color = ColorUtil.BLUE;
		
		steeringDirection = 0;
		maximumSpeed = 50;
		energyLevel = 50;
		energyConsumptionRate = 5;
		damageLevel = 0;
		lastBaseReached = 1;
		
		heading = 0;
		speed = 1;
	}
	
	public Robot() {
		
	}

	public void changeDirection(int amount) {
		// + amount means to the right
		steeringDirection = heading + amount;
		if (steeringDirection < 0) {
			steeringDirection += 360;
		}
	}
	
	public void updateHeading() {
		heading = steeringDirection;
	}
	
	public void takeDamage(int amount) {
		damageLevel += amount;
		maximumSpeed = (int) (50 * (1.00 - (float) damageLevel / 100));
		color *= 0.75;
	}
	
	// getters
	public int getLastBaseReached() {
		return lastBaseReached;
	}
	
	public int getMaxSpeed() {
		return maximumSpeed;
	}
	
	public int getSteeringDirection() {
		return steeringDirection;
	}
	
	public int getEnergyLevel() {
		return energyLevel;
	}
	
	public int getDamageLevel() {
		return damageLevel;
	}
	
	// setters
	public void incLastBaseReached() {
		lastBaseReached++;
	}
	
	public void incEnergyLevel(int amount) {
		energyLevel += amount;
	}
	
	public void decEnergyLevel() {
		energyLevel -= energyConsumptionRate;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		
	}
}
