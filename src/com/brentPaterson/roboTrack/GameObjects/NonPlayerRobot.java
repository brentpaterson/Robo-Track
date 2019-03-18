package com.brentPaterson.roboTrack.GameObjects;

import java.util.Random;

import com.brentPaterson.roboTrack.GameObjects.Strategies.AdvanceStrategy;
import com.brentPaterson.roboTrack.GameObjects.Strategies.AttackStrategy;
import com.brentPaterson.roboTrack.GameObjects.Strategies.IStrategy;
import com.codename1.charts.util.ColorUtil;

public class NonPlayerRobot extends Robot {
	private int steeringDirection;
	private int maximumSpeed;
	private int energyLevel;
	private int energyConsumptionRate;
	private int damageLevel;
	private IStrategy strat;
	
	public NonPlayerRobot() {
		size = 40;
		color = ColorUtil.YELLOW;
		
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
		
		// set initial random strategy
		IStrategy randStrat = null;
		
		int tempRand = rand.nextInt(1);
		if (tempRand == 0)
			randStrat = new AttackStrategy(this);
		else if (tempRand == 1) 
			randStrat = new AdvanceStrategy(this);
		
		this.setStrategy(randStrat);
		this.invokeStrategy();
	}
	
	public void changeDirection(int angle) {
		steeringDirection = angle;
		this.updateHeading();
	}
	
	public void setStrategy(IStrategy strat) {
		this.strat = strat;
	}
	
	public void invokeStrategy() {
		this.strat.apply();
	}
}
