package com.brentPaterson.roboTrack.GameObjects;

public abstract class GameObject {
	protected int size;
	protected float location[];
	protected int color;
	
	// public functions
	public int getSize() {
		return size;
	}
	
	public float[] getLocation() {
		return location;
	}

	public int getColor() {
		return color;
	}
}
