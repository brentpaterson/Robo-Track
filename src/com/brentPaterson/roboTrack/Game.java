package com.brentPaterson.roboTrack;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String; 


public class Game extends Form {
	
	// static game values
	public final static float rangeX = 1024;
	public final static float rangeY = 768;
	
	private GameWorld gw;
	
	public Game() {
		gw = new GameWorld();
		gw.init();
		play();
	}
	
	@SuppressWarnings("rawtypes")
	private void play() {
		// accepts keyboard commands from the player
		// and invokes appropriate methods in GameWorld to manipulate and display
		// the data and tgame state values in the game model
		
		Label myLabel=new Label("Enter a Command:");
		this.addComponent(myLabel);
		final TextField myTextField=new TextField();
		this.addComponent(myTextField);
		this.show();
		 
		myTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
		
				//String sCommand=myTextField.getText().toString();
				char[] sCommand = myTextField.getText().toCharArray();
				myTextField.clear();
				boolean exit = false;
				switch (sCommand[0]) {
						case 'a': // accelerate
							gw.accelerate();
							break;
						case 'b': // break
							gw.brake();
							break;
						case 'l': // steer 5 degrees left
							gw.turnLeft();
							break;
						case 'r': // steer 5 degrees right
							gw.turnRight();
							break;
						 
						// beyond actual input	 
						case 'c': // collision occurred with another robot
							gw.collision();
							break;
						 
						// reached base #
						case '1': // reached base 1
							gw.baseCollision(1);
							break;
						case '2': // reached base 2
							gw.baseCollision(2);
							break;
						case '3': // reached base 3
							gw.baseCollision(3);
							break;
						case '4': // reached base 4
							gw.baseCollision(4);
							break;
						case '5': // reached base 5
							gw.baseCollision(5);
							break;
						case '6': // reached base 6
							gw.baseCollision(6);
							break;
						case '7': // reached base 7
							gw.baseCollision(7);
							break;
						case '8': // reached base 8
							gw.baseCollision(8);
							break;
						case '9': // reached base 9
							gw.baseCollision(9);
							break;
						 
						case 'e': // reached energy station
							gw.energyCollision();
							break;
						case 'g': // collided with drone
							gw.collision();
							break;
						case 't': // game clock tick
							gw.tick();
							break;
						case 'd': // display console text
							gw.consoleDisplay();
							break;
						case 'm': // output map
							gw.displayConsoleMap();
							break;
						 
						case 'x':
							exit = true;
							break;
						case 'y':
							if (exit)
								gw.exit();
							else
								gw.invalidCommand();
							break;
						case 'n':
							if (exit)
								exit = false;
							else
								gw.invalidCommand();
							break;
						
				} //switch
			} //actionPerformed
		} //new ActionListener()
		); //addActionListener 
				
	}
}
