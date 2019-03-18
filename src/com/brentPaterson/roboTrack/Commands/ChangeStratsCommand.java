package com.brentPaterson.roboTrack.Commands;

import com.brentPaterson.roboTrack.GameWorld;
import com.brentPaterson.roboTrack.Collection.IIterator;
import com.brentPaterson.roboTrack.GameObjects.GameObject;
import com.brentPaterson.roboTrack.GameObjects.NonPlayerRobot;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ChangeStratsCommand extends Command {
	private GameWorld gwc;
	
	public ChangeStratsCommand(GameWorld gwi) {
		super("Change Strats");
		
		gwc = gwi;
	}
	
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Change Strats command invoked");
		
		IIterator gameObjects = gwc.getIterator();
		
		while (gameObjects.hasNext()) {
			GameObject curObject = (GameObject) gameObjects.getNext();
			if (curObject instanceof NonPlayerRobot)
				((NonPlayerRobot) curObject).setStrategy();
		}
		
	}
}
