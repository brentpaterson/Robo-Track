package com.brentPaterson.roboTrack;

import com.brentPaterson.roboTrack.GameObjects.Base;
import com.brentPaterson.roboTrack.GameObjects.Drone;
import com.brentPaterson.roboTrack.GameObjects.EnergyStation;
import com.brentPaterson.roboTrack.GameObjects.GameObject;
import com.brentPaterson.roboTrack.GameObjects.Robot;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import java.util.LinkedList;
import java.util.List; 


public class GameWorld {
	private int lives = 3;
	private int time = 0;
	private int topBase = 1;
	
	// game objects
	Robot playerRobot;
	
	List<GameObject> gameObjects = new LinkedList<>();
	
	
	
	public void init() {
		if (lives <= 0) {
			System.out.println("completely out of lives. Exiting program");
			exit();
		}
		
		float[] location = {(float) (0.25 * Game.rangeX), (float) (0.25 * Game.rangeY)};
		gameObjects.add(new Robot(location));
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
		
		playerRobot = (Robot) gameObjects.get(0);
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
		for (GameObject g : gameObjects) {
			if (g instanceof EnergyStation && !((EnergyStation) g).isUsed()) {
				playerRobot.incEnergyLevel(((EnergyStation) g).useStation());
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
		
		for (GameObject g : gameObjects) {
			if (g instanceof Drone) {
				((Drone) g).updateHeading();
			}
		}
		
		playerRobot.move();
		
		for (GameObject g : gameObjects) {
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
		for (GameObject g : gameObjects) {
			if (g instanceof Base) {
				System.out.print("Base: ");
			} else if (g instanceof Robot) {
				System.out.print("Robot: ");
			} else if (g instanceof Drone) {
				System.out.print("Drone: ");
			} else if (g instanceof EnergyStation) {
				System.out.print("EnergyStation: ");
			}
			
			System.out.print("loc=" + g.getLocation().toString());
			
			System.out.print(" color=[" + ColorUtil.red(g.getColor()) + "," + ColorUtil.green(g.getColor())
					+ "," + ColorUtil.blue(g.getColor()) + "]");
		}
	}
	
	public void exit() {
		System.exit(0);
	}
	
	public void invalidCommand() {
		System.out.println("INVALID ENTRY");
	}
}
