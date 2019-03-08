package com.brentPaterson.roboTrack.GameObjects;

public abstract class Movable extends GameObject {
	protected int heading;
	protected int speed;
	
	public void move() {
		location[0] = (float) (location[0] + Math.cos(Math.toRadians(90) - Math.toRadians(heading)) * speed);
		location[1] = (float) (location[1] + Math.sin(Math.toRadians(90) - Math.toRadians(heading)) * speed);
	}
	
	public void accelerate() {
		speed += 1;
	}
	
	public void brake() {
		speed -= 1;
	}
	
	public int getHeading() {
		return heading;
	}
	
	public int getSpeed() {
		return speed;
	}
}
