package com.brentPaterson.roboTrack;

import com.brentPaterson.roboTrack.Collection.GameObjectCollection;
import com.brentPaterson.roboTrack.Collection.IIterator;
import com.brentPaterson.roboTrack.GameObjects.Base;
import com.brentPaterson.roboTrack.GameObjects.Drone;
import com.brentPaterson.roboTrack.GameObjects.EnergyStation;
import com.brentPaterson.roboTrack.GameObjects.GameObject;
import com.brentPaterson.roboTrack.GameObjects.Movable;
import com.brentPaterson.roboTrack.GameObjects.Robot;
import com.codename1.charts.util.ColorUtil;
import java.util.Arrays;
import java.util.Observable; 


public class GameWorld extends Observable {
	private int lives = 3;
	private int time = 0;
	private int topBase = 1;
	
	// game objects
	Robot playerRobot;
	
	GameObjectCollection gameObjects = new GameObjectCollection();
	
	public void init() {
		if (lives <= 0) {
			System.out.println("completely out of lives. Exiting program");
			exit();
		}
		
		float[] location = {(float) (0.25 * Game.rangeX), (float) (0.25 * Game.rangeY)};
		playerRobot = new Robot(location);
		gameObjects.add(playerRobot);
		gameObjects.add(new Base(1, location));
		location[0] = (float) (0.15 * Game.rangeX);
		location[1] = (float) (0.80 * Game.rangeY);
		gameObjects.add(new Base(2, location));
		location[0] = (float) (0.75 * Game.rangeX);
		location[1] = (float) (0.75 * Game.rangeY);
		gameObjects.add(new Base(3, location));
		location[0] = (float) (0.85 * Game.rangeX);
		location[1] = (float) (0.30 * Game.rangeY);
		gameObjects.add(new Base(4, location));
		gameObjects.add(new Drone());
		gameObjects.add(new Drone());
		gameObjects.add(new EnergyStation());
		gameObjects.add(new EnergyStation());
		
		
	}

	public void accelerate() {
		playerRobot.accelerate();
	}
	
	public void brake() {
		playerRobot.brake();
	}
	
	public void turnLeft() {
		playerRobot.changeDirection(-5);
	}
	
	public void turnRight() {
		playerRobot.changeDirection(5);
	}
	
	public void collision() {
		playerRobot.takeDamage(10);
	}
	
	public void baseCollision(int baseNum) {
		if (baseNum == playerRobot.getLastBaseReached() + 1) {
			playerRobot.incLastBaseReached();
			if (baseNum > topBase) {
				topBase = baseNum;
			}
		}
	}
	
	public void energyCollision() {
		IIterator theElements = gameObjects.getIterator();
		while(theElements.hasNext()) {
			GameObject g = (GameObject) theElements.getNext();
			if (g instanceof EnergyStation && !((EnergyStation) g).isUsed()) {
				playerRobot.incEnergyLevel(((EnergyStation) g).useStation());
				gameObjects.add(new EnergyStation());
				break;
			}
		}
	}
	
	public void tick() {
		// game over??
		if (playerRobot.getDamageLevel() >= 100 || playerRobot.getEnergyLevel() <= 0 || playerRobot.getMaxSpeed() <= 0) {
			System.out.println("GAME OVER loser");
			lives--;
			init();
		}
		
		playerRobot.updateHeading();
		
		IIterator theElements = gameObjects.getIterator();
		while(theElements.hasNext()) {
			GameObject g = (GameObject) theElements.getNext();
			if (g instanceof Drone) {
				((Drone) g).updateHeading();
			}
		}
		
		playerRobot.move();
		
		theElements = gameObjects.getIterator();
		while(theElements.hasNext()) {
			GameObject g = (GameObject) theElements.getNext();
			if (g instanceof Drone) {
				((Drone) g).move();
			}
		}
		
		playerRobot.decEnergyLevel();
			
		time++;
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
	
	public void invalidCommand() {
		System.out.println("INVALID ENTRY");
	}
}
