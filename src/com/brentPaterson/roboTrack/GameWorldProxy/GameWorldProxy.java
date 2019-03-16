package com.brentPaterson.roboTrack.GameWorldProxy;

import java.util.Observable;

import com.brentPaterson.roboTrack.GameWorld;
import com.brentPaterson.roboTrack.Collection.GameObjectCollection;
import com.brentPaterson.roboTrack.Collection.IIterator;
import com.brentPaterson.roboTrack.GameObjects.GameObject;
import com.brentPaterson.roboTrack.GameObjects.Robot;

public class GameWorldProxy extends Observable implements IGameWorld {

	private GameWorld realGameWorld;
	
	public GameWorldProxy(GameWorld gw) {
		realGameWorld = gw;
	}
	
	@Override
	public IIterator getIterator() {
		return realGameWorld.getIterator();
	}

	@Override
	public void addGameObject(GameObject o) {
		realGameWorld.addGameObject(o);
	}

	@Override
	public boolean removeGameObject(GameObject o) {
		return false;
	}
	
	public int getLives() {
		return realGameWorld.getLives();
	}
	
	public int getTime() {
		return realGameWorld.getTime();
	}
	
	public int getTopBase() {
		return realGameWorld.getTopBase();
	}
	
	public GameObjectCollection getCollection() {
		return realGameWorld.getCollection();
	}
	
	public Robot getPlayerRobot() {
		return realGameWorld.getPlayerRobot();
	}

}
