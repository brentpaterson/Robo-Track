package com.brentPaterson.roboTrack.Commands;

import com.brentPaterson.roboTrack.GameWorld;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command {

	
	public AboutCommand(GameWorld gwi) {
		super("About");
		
		// game world unneeded. Can be passed I guess but won't do anything
	}
	
	public AboutCommand() {
		super("About");
	}
	
	public void actionPerformed(ActionEvent ev) {
		System.out.println("About command invoked (not implemented)");

		Command cOk = new Command("Ok");
		Command[] cmds = new Command[] {cOk};
		String body = "Author: Brent Paterson\n"
				+ "Course: CSC133-03\n"
				+ "Version: 0.30\n"
				+ "Music and sounds by Christo Savaides";
		
		Dialog.show("About", body, cmds);
		
		
	}
}
