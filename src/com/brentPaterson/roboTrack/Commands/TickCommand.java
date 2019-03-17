package com.brentPaterson.roboTrack.Commands;

import com.brentPaterson.roboTrack.GameWorld;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TickCommand extends Command {
	private GameWorld gwc;
	
	public TickCommand(GameWorld gwi) {
		super("Tick");
		
		gwc = gwi;
	}
	
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Tick command invoked");
		gwc.tick();
	}
}
