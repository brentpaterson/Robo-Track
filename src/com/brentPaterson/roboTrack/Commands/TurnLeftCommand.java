package com.brentPaterson.roboTrack.Commands;

import com.brentPaterson.roboTrack.GameWorld;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TurnLeftCommand extends Command {
	private GameWorld gwc;
	
	public TurnLeftCommand(GameWorld gwi) {
		super("Turn Left");
		
		gwc = gwi;
	}
	
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Turn Left command invoked");
		gwc.turnLeft();
	}
}
