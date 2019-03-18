package com.brentPaterson.roboTrack.Commands;

import com.brentPaterson.roboTrack.GameWorld;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command {
	private GameWorld gwc;
	
	public ExitCommand(GameWorld gwi) {
		super("Exit");
		
		gwc = gwi;
	}
	
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Exit game command invoked, displaying dialog...");
		
		Command cConfirm = new Command("Exit game");
		Command cDeny = new Command("Cancel");
		Command[] cmds = new Command[] {cConfirm, cDeny};
		
		Command c = Dialog.show("Are you sure you want to exit?",  "", cmds);
		
		if (c == cConfirm) {
			System.out.println("Exit game confirmed, ending game...");
			gwc.exit();
		} else {
			System.out.println("Exit game canceled");
		}
	}
}
