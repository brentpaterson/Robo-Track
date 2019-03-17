package com.brentPaterson.roboTrack.Commands;

import com.brentPaterson.roboTrack.GameWorld;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollideESCommand extends Command {
	private GameWorld gwc;
	
	public CollideESCommand(GameWorld gwi) {
		super("Collide Energy Station");
		
		gwc = gwi;
	}
	
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Collide Enery Station command invoked");
		gwc.energyCollision();
	}
}
