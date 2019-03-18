package com.brentPaterson.roboTrack.Commands;

import com.brentPaterson.roboTrack.GameWorld;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command {
	private GameWorld gwc;
	
	public AboutCommand(GameWorld gwi) {
		super("About");
		
		gwc = gwi;
	}
	
	public void actionPerformed(ActionEvent ev) {
		System.out.println("About command invoked (not implemented)");
		// to be implemented
	}
}
