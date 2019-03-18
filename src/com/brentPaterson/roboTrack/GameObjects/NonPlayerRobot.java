package com.brentPaterson.roboTrack.GameObjects;

import java.util.Random;

import com.brentPaterson.roboTrack.Collection.IIterator;
import com.brentPaterson.roboTrack.GameObjects.Strategies.AdvanceStrategy;
import com.brentPaterson.roboTrack.GameObjects.Strategies.AttackStrategy;
import com.brentPaterson.roboTrack.GameObjects.Strategies.IStrategy;
import com.brentPaterson.roboTrack.GameWorldProxy.GameWorldProxy;
import com.codename1.charts.util.ColorUtil;

public class NonPlayerRobot extends Robot {
	private int steeringDirection;
	private int maximumSpeed;
	private int energyLevel;
	private int energyConsumptionRate;
	private int damageLevel;

	private IStrategy strat;
	
	private GameWorldProxy gw;
	
	public NonPlayerRobot(GameWorldProxy gw) {
		size = 40;
		color = ColorUtil.YELLOW;
		
		steeringDirection = 0;
		maximumSpeed = 50;
		energyLevel = 50;
		energyConsumptionRate = 5;
		damageLevel = 0;
		
		heading = 0;
		speed = 1;
		
		this.gw = gw;
		
		
		Random rand = new Random();
		int angle = rand.nextInt(91);
		int distance = rand.nextInt(size*3) + size*3; // anywhere from 120-240 units away
		location = new float[2];
		location[0] = (float) Math.cos(Math.toRadians(angle)) * distance;
		location[1] = (float) Math.sin(Math.toRadians(angle)) * distance;
		
		float[] locations = getBaseLocation();
		float[] attackLocation = new float[] {locations[0], locations[1]};
		float[] advanceLocation = new float[] {locations[2], locations[3]};
		
		int tempRand = rand.nextInt(2);
		if (tempRand == 0)
			strat = new AttackStrategy(this, attackLocation);
		else if (tempRand == 1) 
			strat = new AdvanceStrategy(this, advanceLocation);
		this.invokeStrategy();
		
		
	}
	
	public void changeDirection(int angle) {
		steeringDirection = angle;
		System.out.println("new direction: " + steeringDirection);
		this.updateHeading();
	}
	
	public void setStrategy() {
		float[] locations = getBaseLocation();
		float[] attackLocation = new float[] {locations[0], locations[1]};
		float[] advanceLocation = new float[] {locations[2], locations[3]};
		
		
		if (strat instanceof AttackStrategy)
			strat = new AdvanceStrategy(this, advanceLocation);
		else
			strat = new AttackStrategy(this, attackLocation);
	}
	
	public void invokeStrategy() {
		this.strat.apply();
	}
	
	public void decEnergyLevel() {
		// do nothing
	}
	
	public void takeDamage(int amount) {
		// do nothing
	}
	
	// helper method
	private float[] getBaseLocation() {
		IIterator gwIterator = gw.getIterator();
		float[] locations = new float[4];
		while (gwIterator.hasNext()) {
			GameObject curObj = (GameObject) gwIterator.getNext();
			if (curObj instanceof Base) {
				if (((Base) curObj).getSeqNum() == gw.getTopBase()) {
					locations[0] = curObj.getLocation()[0];
					locations[1] = curObj.getLocation()[1];
				} else if (((Base) curObj).getSeqNum() == gw.getTopBase() + 1) {
					locations[2] = curObj.getLocation()[0];
					locations[3] = curObj.getLocation()[1];
				}
			}
		}
		
		return locations;
	}
}

