package com.brentPaterson.roboTrack.GameObjects.Interfaces;

import com.brentPaterson.roboTrack.GameObjects.GameObject;

public interface ICollider {
	public boolean collidesWith(GameObject otherObject);
	public void handleCollision(GameObject otherObject);
}
