package com.brentPaterson.roboTrack.Commands;

import com.brentPaterson.roboTrack.Game;
import com.brentPaterson.roboTrack.GameWorld;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SoundToggleCommand extends Command {
	private GameWorld gwc;
	
	public SoundToggleCommand(GameWorld gwi) {
		super("Sound Toggle");
		
		gwc = gwi;
	}
	
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Sound toggle command invoked (currently not implemented, only changes string to on/off)");
		gwc.setSoundToggle();
	}
}
