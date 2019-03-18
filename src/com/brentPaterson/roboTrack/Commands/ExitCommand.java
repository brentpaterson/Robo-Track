package com.brentPaterson.roboTrack.Commands;

import com.brentPaterson.roboTrack.GameWorld;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command {
	private GameWorld gwc;
	
	public ExitCommand(GameWorld gwi) {
		super("Exit");
		
		gwc = gwi;
	}
	
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Exit game command invoked, displaying dialog...");
		
	}
}
