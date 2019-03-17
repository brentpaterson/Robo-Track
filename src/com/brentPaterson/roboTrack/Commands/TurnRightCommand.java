package com.brentPaterson.roboTrack.Commands;

import com.brentPaterson.roboTrack.GameWorld;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TurnRightCommand extends Command {
	private GameWorld gwc;
	
	public TurnRightCommand(GameWorld gwi) {
		super("Turn Right");
		
		gwc = gwi;
	}
	
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Turn Right command invoked");
		gwc.turnRight();
	}
}
