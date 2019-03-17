package com.brentPaterson.roboTrack.Commands;

import com.brentPaterson.roboTrack.GameWorld;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class BrakeCommand extends Command {
	private GameWorld gwc;
	
	public BrakeCommand(GameWorld gwi) {
		super("Brake");
		
		gwc = gwi;
	}
	
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Brake command invoked");
		gwc.brake();
	}
}
