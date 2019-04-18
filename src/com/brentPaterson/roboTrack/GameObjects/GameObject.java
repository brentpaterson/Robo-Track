package com.brentPaterson.roboTrack.GameObjects;

import com.brentPaterson.roboTrack.GameObjects.Interfaces.ICollider;
import com.brentPaterson.roboTrack.GameObjects.Interfaces.IDrawable;

public abstract class GameObject implements IDrawable, ICollider {
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
