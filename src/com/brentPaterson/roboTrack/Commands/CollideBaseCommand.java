package com.brentPaterson.roboTrack.Commands;

import com.brentPaterson.roboTrack.GameWorld;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class CollideBaseCommand extends Command {
	private GameWorld gwc;
	
	public CollideBaseCommand(GameWorld gwi) {
		super("Collide Base");
		
		gwc = gwi;
	}
	
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Collide Base command invoked");
		
		Command cOk = new Command("Ok");
		Command cCancel = new Command("Cancel");
		Command[] cmds = new Command[] {cOk, cCancel};
		int baseNum;
		TextField myTF = new TextField();
		Command c = Dialog.show("Enter base number: ", myTF, cmds);
				
		if (c == cOk) {
			baseNum = Integer.parseInt(myTF.getText());
			System.out.println("Colliding with base " + baseNum);
			gwc.baseCollision(baseNum);
		} else {
			System.out.println("No baseNum given, cancelling command");
		}
		
	}
}
