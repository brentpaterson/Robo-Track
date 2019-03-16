package com.brentPaterson.roboTrack.GameWorldProxy;

import java.util.Iterator;

import com.brentPaterson.roboTrack.Collection.IIterator;
import com.brentPaterson.roboTrack.GameObjects.GameObject;

public interface IGameWorld {
	@SuppressWarnings("rawtypes")
	IIterator getIterator();
	void addGameObject(GameObject o);
	boolean removeGameObject(GameObject o);
}
