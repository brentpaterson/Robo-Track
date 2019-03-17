package com.brentPaterson.roboTrack.Commands;

import com.brentPaterson.roboTrack.GameWorld;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollideDroneCommand extends Command {
	private GameWorld gwc;
	
	public CollideDroneCommand(GameWorld gwi) {
		super("Collide Drone");
		
		gwc = gwi;
	}
	
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Collide Drone command invoked");
		gwc.collisionDrone();
	}
}
