package com.brentPaterson.roboTrack.GameObjects.Strategies;

import com.brentPaterson.roboTrack.GameObjects.NonPlayerRobot;
import com.codename1.util.MathUtil;

public class AttackStrategy implements IStrategy {
	
	private NonPlayerRobot npr;
	private float[] location;

	public AttackStrategy(NonPlayerRobot npr, float[] location) {
		this.npr = npr;
		this.location = new float[2];
		this.location[0] = location[0];
		this.location[1] = location[1];
	}

	@Override
	public void apply() {
		System.out.println("Applying attack strategy");
		// find direction to current base
		float x = location[0] - npr.getLocation()[0];
		float y = location[1] - npr.getLocation()[1];
		
		double angle = 90 - Math.toDegrees(MathUtil.atan2((double) x, (double) y)); 
		
		// set heading
		npr.changeDirection((int) angle);
		
	}



}
