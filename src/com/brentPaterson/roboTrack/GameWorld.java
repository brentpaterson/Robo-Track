package com.brentPaterson.roboTrack;

import com.brentPaterson.roboTrack.Collection.GameObjectCollection;
import com.brentPaterson.roboTrack.Collection.IIterator;
import com.brentPaterson.roboTrack.GameObjects.Base;
import com.brentPaterson.roboTrack.GameObjects.Drone;
import com.brentPaterson.roboTrack.GameObjects.EnergyStation;
import com.brentPaterson.roboTrack.GameObjects.GameObject;
import com.brentPaterson.roboTrack.GameObjects.Movable;
import com.brentPaterson.roboTrack.GameObjects.NonPlayerRobot;
import com.brentPaterson.roboTrack.GameObjects.Robot;
import com.brentPaterson.roboTrack.GameObjects.Interfaces.ICollider;
import com.brentPaterson.roboTrack.GameWorldProxy.GameWorldProxy;
import com.brentPaterson.roboTrack.GameWorldProxy.IGameWorld;
import com.brentPaterson.roboTrack.Sounds.Sound;
import com.codename1.charts.util.ColorUtil;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector; 


public class GameWorld extends Observable implements IGameWorld {
	private int lives;
	private int time;
	private int topBase;
	private boolean soundStatus;
	private int tickRate;
	
	// game objects
	private Robot playerRobot;
	private GameObjectCollection gameObjects = new GameObjectCollection();
	private Vector<Observer> myObserverList = new Vector<Observer>();
	
	public GameWorld() {
		lives = 3;
		time = 0;
		topBase = 1;
		soundStatus = true;
		tickRate = 20;
	}
	
	/*********************************************************
	 * 
	 * Start Function
	 * 
	 *********************************************************/
	
	public void init() {
		if (lives <= 0) {
			System.out.println("completely out of lives. Exiting program");
			exit();
		}
		
		GameWorldProxy proxy = new GameWorldProxy(this);
		
		float[] mapResolution = Game.getMapResolution();
		float[] location = {(float) (0.25 * mapResolution[0]), (float) (0.25 * mapResolution[1])};
		playerRobot = new Robot(location, proxy);
		gameObjects.add(playerRobot);
		gameObjects.add(new Base(1, location, proxy));
		location[0] = (float) (0.15 * mapResolution[0]);
		location[1] = (float) (0.80 * mapResolution[1]);
		gameObjects.add(new Base(2, location, proxy));
		location[0] = (float) (0.75 * mapResolution[0]);
		location[1] = (float) (0.75 * mapResolution[1]);
		gameObjects.add(new Base(3, location, proxy));
		location[0] = (float) (0.85 * mapResolution[0]);
		location[1] = (float) (0.30 * mapResolution[1]);
		gameObjects.add(new Base(4, location, proxy));
		gameObjects.add(new Drone(proxy));
		gameObjects.add(new Drone(proxy));
		gameObjects.add(new EnergyStation(proxy));
		gameObjects.add(new EnergyStation(proxy));		
		
		gameObjects.add(new NonPlayerRobot(proxy));
		gameObjects.add(new NonPlayerRobot(proxy));
		
		notifyObservers();
	}
	
	/*********************************************************
	 * 
	 * button Functions
	 * 
	 *********************************************************/

	public void accelerate() {
		playerRobot.accelerate();
	}
	
	public void brake() {
		playerRobot.brake();
	}
	
	public void turnLeft() {
		playerRobot.changeDirection(-15);
	}
	
	public void turnRight() {
		playerRobot.changeDirection(15);
	}
	
	
	/*********************************************************
	 * 
	 * Collision handling Functions
	 * 
	 *********************************************************/
	
	public void collideWithRobot(GameObject obj, GameObject otherObj) {
		if (obj instanceof Movable) {
			((Movable) obj).bounce();
			if (obj instanceof Robot) {
				((Robot) obj).changeDirection(0);
				((Robot) obj).takeDamage(25);
			}
		}
		
		if (otherObj instanceof Movable) {
			((Movable) otherObj).bounce();
			if (otherObj instanceof Robot) {
				((Robot) otherObj).changeDirection(0);
				((Robot) otherObj).takeDamage(25);
			}
		}
		
		if (obj instanceof Base) {
			this.collideWithBase(otherObj, obj);
		}
		if (obj instanceof EnergyStation) {
			this.collideWithEnergyStation(otherObj, obj);
		}
	}
	
	public void collideWithBase(GameObject obj, GameObject otherObj) {
		if (obj instanceof Robot) {
			if (((Robot) obj).getLastBaseReached() == ((Base) otherObj).getSeqNum() - 1)
				((Robot) obj).incLastBaseReached();
		}
		
	}
	
	public void collideWithDrone(GameObject obj, GameObject otherObj) {
		if (obj instanceof Robot) {
			this.collideWithRobot(otherObj, obj);
		}
	}
	
	public void collideWithEnergyStation(GameObject obj, GameObject otherObj) {
		if (obj instanceof Robot && !((EnergyStation) otherObj).isUsed()) {
			((Robot) obj).incEnergyLevel(((EnergyStation) otherObj).useStation());
			
		}
	}
	
	
	/*********************************************************
	 * 
	 * Tick Functions
	 * 
	 *********************************************************/
	
	public void death() {
		System.out.println("GAME OVER loser");
		lives--;
		
		gameObjects.clear();	
		
		init();
	}
	
	public void tick() {
		// game over??
		if (playerRobot.getDamageLevel() >= 100 || playerRobot.getEnergyLevel() <= 0 || playerRobot.getMaxSpeed() <= 0) {
			death();
		}
		
		playerRobot.updateHeading();
		
		IIterator theElements = gameObjects.getIterator();
		while(theElements.hasNext()) {
			GameObject g = (GameObject) theElements.getNext();
			if (g instanceof Drone) {
				((Drone) g).updateHeading();
			}
		}
		
		playerRobot.move(tickRate);
		
		theElements = gameObjects.getIterator();
		while(theElements.hasNext()) {
			GameObject g = (GameObject) theElements.getNext();
			if (g instanceof Drone) {
				((Drone) g).move(tickRate);
			}
		}
		
		playerRobot.decEnergyLevel();
		
		// check for collisions
		theElements = gameObjects.getIterator();
		
		while (theElements.hasNext()) {
			IIterator theElements2 = gameObjects.getIterator();
			GameObject otherObj = (GameObject) theElements.getNext();
			while (theElements2.hasNext()) {
				ICollider curObj = (ICollider) theElements2.getNext();
				if (curObj != (ICollider) otherObj && curObj.collidesWith(otherObj)) {
					curObj.handleCollision(otherObj);
				}
			}
			
		}
			
		time++;
		notifyObservers();
	}
	
	public int getTickRate() {
		return tickRate;
	}
	
	public void consoleDisplay() {
		System.out.println("Lives: " + lives);
		System.out.println("Time: " + time);
		System.out.println("Top base: " + topBase);
		System.out.println("Current energy level: " + playerRobot.getEnergyLevel());
		System.out.println("Current damage level: " + playerRobot.getDamageLevel());
	}
	
	public void displayConsoleMap() {
		IIterator theElements = gameObjects.getIterator();
		while(theElements.hasNext()) {
			GameObject g = (GameObject) theElements.getNext();
			if (g instanceof Base) {
				System.out.print("Base: ");
			} else if (g instanceof NonPlayerRobot){
				System.out.print("NonPlayerRobot: ");
			} else if (g instanceof Robot) {
				System.out.print("Robot: ");
			} else if (g instanceof Drone) {
				System.out.print("Drone: ");
			} else if (g instanceof EnergyStation) {
				System.out.print("EnergyStation: ");
			}
			
			System.out.print("loc=" + Arrays.toString(g.getLocation()));
			
			System.out.print(" color=[" + ColorUtil.red(g.getColor()) + "," + ColorUtil.green(g.getColor())
					+ "," + ColorUtil.blue(g.getColor()) + "]");
			
			// movable specific stats
			if (g instanceof Movable) {
				System.out.print(" heading=" + ((Movable) g).getHeading());
				System.out.print(" speed=" + ((Movable) g).getSpeed());
			}
			
			System.out.print(" size=" + g.getSize());
			
			// max speed, steering dir, energy level, damage level
			// robot specific stats
			if (g instanceof Robot) {
				System.out.print(" maxSpeed=" + ((Robot) g).getMaxSpeed());
				System.out.print(" steeringDirection=" + ((Robot) g).getSteeringDirection());
				System.out.print(" energyLevel=" + ((Robot) g).getEnergyLevel());
				System.out.print(" damageLevel=" + ((Robot) g).getDamageLevel());
			}
			
			// seqnum base
			if (g instanceof Base) {
				System.out.print(" seqNum=" + ((Base) g).getSeqNum());
			}
			
			// capacity energyStation
			if (g instanceof EnergyStation) {
				System.out.print(" capacity=" + ((EnergyStation) g).getCapacity());
			}
			
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	public void exit() {
		System.exit(0);
	}
	
	public void setSoundToggle() {
		soundStatus = !soundStatus;
		
		notifyObservers();
	}
	
	/*********************************************************
	 * 
	 * Getters for view Functions
	 * 
	 *********************************************************/
	
	
	public int getLives() {
		return lives;
	}
	
	public int getTime() {
		return time;
	}
	
	public int getTopBase() {
		return topBase;
	}
	
	public boolean getSoundStatus() {
		return soundStatus;
	}
	
	public GameObjectCollection getCollection() {
		return gameObjects;
	}
	
	public Robot getPlayerRobot() {
		return playerRobot;
	}

	public IIterator getIterator() {
		return gameObjects.getIterator();
	}

	public void addGameObject(GameObject o) {
		gameObjects.add(o);
		
	}

	public boolean removeGameObject(GameObject o) {
		return gameObjects.remove(o);
	}
	
	public void addObserver(Observer o) {
		myObserverList.add(o);
	}
	
	public void notifyObservers() {
		GameWorldProxy proxy = new GameWorldProxy(this);
		for (Observer o : myObserverList) {
			o.update(proxy, null);
		}
	}
	
	/*********************************************************
	 * 
	 * Sound Functions
	 * 
	 *********************************************************/
	
	public void chargeUpSound() {
		if (soundStatus) {
			Sound chargeUp = new Sound("Charge Up Noise.wav");
			chargeUp.play();
		}
	}
}
