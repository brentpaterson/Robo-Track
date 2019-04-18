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
	
	@Override
	public boolean collidesWith(GameObject otherObject) {
		int otherX = (int) otherObject.getLocation()[0];
		int otherY = (int) otherObject.getLocation()[1];
		
		if (this.getLocation()[0] > otherX && this.getLocation()[0] < otherX + otherObject.getSize()) {
			if (this.getLocation()[1] > otherY && this.getLocation()[1] < otherY + otherObject.getSize())
				return true;
		}
		
		
		return false;
	}
}
