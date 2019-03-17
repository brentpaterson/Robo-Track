package com.brentPaterson.roboTrack.Commands;

import com.brentPaterson.roboTrack.GameWorld;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class HelpCommand extends Command {
	private GameWorld gwc;
	
	public HelpCommand(GameWorld gwi) {
		super("Help");
		
		gwc = gwi;
	}
	
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Help command invoked (currently not implemented)");
		// does nothing currently
	}
}
