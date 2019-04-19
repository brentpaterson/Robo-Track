package com.brentPaterson.roboTrack.GameObjects;

import com.brentPaterson.roboTrack.GameObjects.Interfaces.ISteerable;
import com.brentPaterson.roboTrack.GameWorldProxy.GameWorldProxy;
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

	private GameWorldProxy gw;
	
	public Robot(float[] initialLocation, GameWorldProxy gw) {
		location = new float[2];
		location[0] = initialLocation[0];
		location[1] = initialLocation[1];
		
		size = 40;
		color = ColorUtil.BLUE;
		
		steeringDirection = 0;
		maximumSpeed = 50;
		energyLevel = 500;
		energyConsumptionRate = 1;
		damageLevel = 0;
		lastBaseReached = 1;
		
		heading = 0;
		speed = 1;
		
		this.gw = gw;
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
		g.setColor(this.getColor());
		g.fillRect((int)(pCmpRelPrnt.getX() + this.getLocation()[0]), (int)(pCmpRelPrnt.getY() + this.getLocation()[1]),
				this.getSize(), this.getSize());
		
	}

	@Override
	public void handleCollision(GameObject otherObject) {
		if (otherObject instanceof Robot || otherObject instanceof Drone) {
			this.takeDamage(10);
		}
		
		if (otherObject instanceof EnergyStation) {
			if (!((EnergyStation) otherObject).isUsed())
				((EnergyStation) otherObject).useStation();
		}
		
		if (otherObject instanceof Base) {
			if (this.getLastBaseReached() == ((Base) otherObject).getSeqNum() + 1) {
				this.incLastBaseReached();
			}
		}

	}
}
