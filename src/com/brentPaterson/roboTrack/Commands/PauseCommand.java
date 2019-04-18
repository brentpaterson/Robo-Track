package com.brentPaterson.roboTrack.Commands;

import com.brentPaterson.roboTrack.Game;
import com.brentPaterson.roboTrack.GameWorld;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PauseCommand extends Command {
	private GameWorld gwc;
	private Game g;
	
	public PauseCommand(GameWorld gwi, Game g) {
		super("Pause");
		
		gwc = gwi;
		this.g = g;
	}
	
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Pause command invoked");
		
		g.timerToggle();
	}
}
