package com.brentPaterson.roboTrack.GameObjects;

public abstract class Movable extends GameObject {
	protected int heading;
	protected int speed;
	
	public void move(int elapsedTime) {
		location[0] = (float) (location[0] + Math.cos(Math.toRadians(90)
				- Math.toRadians(heading)) * speed * (elapsedTime / 10));
		location[1] = (float) (location[1] + Math.sin(Math.toRadians(90)
				- Math.toRadians(heading)) * speed * (elapsedTime / 10));
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
