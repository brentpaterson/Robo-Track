package com.brentPaterson.roboTrack.Commands;

import com.brentPaterson.roboTrack.GameWorld;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PauseCommand extends Command {
	private GameWorld gwc;
	
	public PauseCommand(GameWorld gwi) {
		super("Pause");
		
		gwc = gwi;
	}
	
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Pause command invoked (currently not implemented)");
		// does nothing currently
	}
}
