package com.brentPaterson.roboTrack;

import com.brentPaterson.roboTrack.GameObjects.Base;
import com.brentPaterson.roboTrack.GameObjects.Drone;
import com.brentPaterson.roboTrack.GameObjects.EnergyStation;
import com.brentPaterson.roboTrack.GameObjects.Robot;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String; 


public class GameWorld {
	private int lives = 3;
	private int time = 0;
	private int topBase = 1;
	
	// game objects
	Base baseOne, baseTwo, baseThree, baseFour;
	Robot playerRobot;
	Drone droneOne, droneTwo;
	EnergyStation esOne, esTwo;
	
	
	
	public void init() {
		if (lives <= 0) {
			System.out.println("completely out of lives. Exiting program");
			exit();
		}
		
		// create the bases with hard defined locations
		float[] location = {(float) (0.25 * Game.rangeX), (float) (0.25 * Game.rangeY)};
		baseOne = new Base(1, location);
		location[0] = (float) (0.15 * Game.rangeX);
		location[1] = (float) (0.80 * Game.rangeY);
		baseTwo = new Base(2, location);
		location[0] = (float) (0.75 * Game.rangeX);
		location[1] = (float) (0.75 * Game.rangeY);
		baseThree = new Base(3, location);
		location[0] = (float) (0.85 * Game.rangeX);
		location[1] = (float) (0.30 * Game.rangeY);
		baseFour = new Base(4, location);
		
		// create robot (currently only player robot)
		playerRobot = new Robot(baseOne.getLocation());
		
		// create drones
		droneOne = new Drone();
		droneTwo = new Drone();
		
		// create energy stations
		esOne = new EnergyStation();
		esTwo = new EnergyStation();
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
		playerRobot.incEnergyLevel(esOne.getSize()); // input to know which ES to drain?
		
		// create new energy station
		// fade original
	}
	
	public void tick() {
		// game over??
		if (playerRobot.getDamageLevel() >= 100 || playerRobot.getEnergyLevel() <= 0 || playerRobot.getMaxSpeed() <= 0) {
			System.out.println("GAME OVER loser");
			lives--;
			init();
		}
		
		playerRobot.updateHeading();
		droneOne.updateHeading();
		droneTwo.updateHeading();
		
		playerRobot.move();
		droneOne.move();
		droneTwo.move();
		
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
		System.out.println("Base: loc=" + baseOne.getLocation().toString() + " color=" + baseOne.getColor() + " size=" + baseOne.getSize() + " seqNum=1");
		System.out.println("Base: loc=" + baseTwo.getLocation().toString() + " color=" + baseTwo.getColor() + " size=" + baseTwo.getSize() + " seqNum=2");
		System.out.println("Base: loc=" + baseThree.getLocation().toString() + " color=" + baseThree.getColor() + " size=" + baseThree.getSize() + " seqNum=3");
		System.out.println("Base: loc=" + baseFour.getLocation().toString() + " color=" + baseFour.getColor() + " size=" + baseFour.getSize() + " seqNum=4");
		
		System.out.println("Robot: loc=" + playerRobot.getLocation().toString() + " color=" + playerRobot.getColor()
				+ " heading=" + playerRobot.getHeading() + " speed=" + playerRobot.getSpeed() + " size=" + playerRobot.getSize()
				+ " maxSpeed=" + playerRobot.getMaxSpeed() + " steeringDirection=" + playerRobot.getSteeringDirection()
				+ " energeyLevel=" + playerRobot.getEnergyLevel() + " damageLevel=" + playerRobot.getDamageLevel());
		
		System.out.println("Drone: loc=" + droneOne.getLocation().toString() + " color=" + droneOne.getColor()
				+ " heading=" + droneOne.getHeading() + " speed=" + droneOne.getSpeed() + " size=" + droneOne.getSize());
		System.out.println("Drone: loc=" + droneTwo.getLocation().toString() + " color=" + droneTwo.getColor()
				+ " heading=" + droneTwo.getHeading() + " speed=" + droneTwo.getSpeed() + " size=" + droneTwo.getSize());
		
		System.out.println("EnergyStation: loc=" + esOne.getLocation().toString() + " color=" + esOne.getColor()
				+ " size=" + esOne.getSize() + " capacity=" + esOne.getSize());
		System.out.println("EnergyStation: loc=" + esTwo.getLocation().toString() + " color=" + esTwo.getColor()
				+ " size=" + esTwo.getSize() + " capacity=" + esTwo.getSize());
	
	}
	
	public void exit() {
		System.exit(0);
	}
	
	public void invalidCommand() {
		System.out.println("INVALID ENTRY");
	}
}
