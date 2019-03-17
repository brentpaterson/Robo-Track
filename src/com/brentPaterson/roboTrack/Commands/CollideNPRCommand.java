package com.brentPaterson.roboTrack.Commands;

import com.brentPaterson.roboTrack.GameWorld;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollideNPRCommand extends Command {
	private GameWorld gwc;
	
	public CollideNPRCommand(GameWorld gwi) {
		super("Collide NPR");
		
		gwc = gwi;
	}
	
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Collide NPR command invoked");
		gwc.collisionNPR();
	}
}
