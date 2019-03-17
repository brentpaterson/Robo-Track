package com.brentPaterson.roboTrack.Commands;

import com.brentPaterson.roboTrack.GameWorld;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AccelerateCommand extends Command {
	private GameWorld gwc;
	
	public AccelerateCommand(GameWorld gwi) {
		super("Accelerate");
		
		gwc = gwi;
	}
	
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Accelerate command invoked");
		gwc.accelerate();
	}
}
