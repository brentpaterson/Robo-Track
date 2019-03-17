package com.brentPaterson.roboTrack.Commands;

import com.brentPaterson.roboTrack.GameWorld;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ChangeStratsCommand extends Command {
	private GameWorld gwc;
	
	public ChangeStratsCommand(GameWorld gwi) {
		super("Change Strats");
		
		gwc = gwi;
	}
	
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Change Strats command invoked (unimplemented)");
		
	}
}
