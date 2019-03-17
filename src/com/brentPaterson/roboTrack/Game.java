package com.brentPaterson.roboTrack;
import com.brentPaterson.roboTrack.Commands.AccelerateCommand;
import com.brentPaterson.roboTrack.Commands.BrakeCommand;
import com.brentPaterson.roboTrack.Commands.ChangeStratsCommand;
import com.brentPaterson.roboTrack.Commands.CollideBaseCommand;
import com.brentPaterson.roboTrack.Commands.CollideDroneCommand;
import com.brentPaterson.roboTrack.Commands.CollideESCommand;
import com.brentPaterson.roboTrack.Commands.CollideNPRCommand;
import com.brentPaterson.roboTrack.Commands.TickCommand;
import com.brentPaterson.roboTrack.Commands.TurnLeftCommand;
import com.brentPaterson.roboTrack.Commands.TurnRightCommand;
import com.brentPaterson.roboTrack.Views.MapView;
import com.brentPaterson.roboTrack.Views.ScoreView;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent; 


public class Game extends Form {
	
	// static game values
	public final static float rangeX = 1024;
	public final static float rangeY = 768;
	
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private Container westContainer, eastContainer, southContainer;
	private Button accelerateButton, turnLeft, turnRight, changeStrats, 
			brake, collideNPR, collideBase, collideES, collideDrone, tick;
	
	// commands
	private AccelerateCommand accelerateCommand;
	private BrakeCommand brakeCommand;
	private ChangeStratsCommand changeStratsCommand;
	private CollideBaseCommand collideBaseCommand;
	private CollideDroneCommand collideDroneCommand;
	private CollideESCommand collideESCommand;
	private CollideNPRCommand collideNPRCommand;
	private TickCommand tickCommand;
	private TurnLeftCommand turnLeftCommand;
	private TurnRightCommand turnRightCommand;
	
	public Game() {
		gw = new GameWorld();
		mv = new MapView(gw);
		sv = new ScoreView(gw);
		
		// create border layout
		System.out.println("Creating BorderLayout");
		this.setLayout(new BorderLayout());
		
		initializeCommands();
		
		// create other containers
		westContainer = new Container();
		eastContainer = new Container();
		southContainer = new Container();
		westContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		eastContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		southContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		
		// set container styles
		this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLUE));
		westContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLUE));
		southContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLUE));
		eastContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLUE));
		
		createButtons();
				
		// adding buttons
		westContainer.add(accelerateButton);
		westContainer.add(turnLeft);
		westContainer.add(changeStrats);
		eastContainer.add(brake);
		eastContainer.add(turnRight);
		southContainer.add(collideNPR);
		southContainer.add(collideBase);
		southContainer.add(collideES);
		southContainer.add(collideDrone);
		southContainer.add(tick);
		
		// add toolbar
		Toolbar myToolbar = new Toolbar();
		myToolbar.setUIID("mainToolbar");
		myToolbar.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLUE));
		this.setToolbar(myToolbar);	
		myToolbar.setTitle("Robo-Track");
		
		// adding containers to regions
		System.out.println("Adding mapview and scoreview");
		this.add(BorderLayout.CENTER, mv);
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.WEST, westContainer);
		this.add(BorderLayout.SOUTH, southContainer);
		this.add(BorderLayout.EAST, eastContainer);
		
		createKeyListeners();
		
		this.show();
		
		
		gw.init();
	}
	public void createButtons() {
		// initialize buttons
		// left container buttons
		accelerateButton = new Button("Accelerate");
		turnLeft = new Button("Left");
		changeStrats = new Button("Change Strategies");
		// right container buttons
		brake = new Button("Brake");
		turnRight = new Button("Right");
		// bottom container buttons
		collideNPR = new Button("Collide with NPR");
		collideBase = new Button("Collide with Base");
		collideES = new Button("Collide with Energy Station");
		collideDrone = new Button("Collide with Drone");
		tick = new Button("Tick");
		
		setButtonCommands();
		
		setButtonStyles(accelerateButton);
		setButtonStyles(turnLeft);
		setButtonStyles(changeStrats);
		setButtonStyles(brake);
		setButtonStyles(turnRight);
		setButtonStyles(collideNPR);
		setButtonStyles(collideBase);
		setButtonStyles(collideES);
		setButtonStyles(collideDrone);
		setButtonStyles(tick);
	}
	
	public void setButtonCommands() {
		accelerateButton.setCommand(accelerateCommand);
		turnLeft.setCommand(turnLeftCommand);
		changeStrats.setCommand(changeStratsCommand);
		brake.setCommand(brakeCommand);
		turnRight.setCommand(turnRightCommand);
		collideNPR.setCommand(collideNPRCommand);
		collideBase.setCommand(collideBaseCommand);
		collideES.setCommand(collideESCommand);
		collideDrone.setCommand(collideDroneCommand);
		tick.setCommand(tickCommand);
	}
	
	public void setButtonStyles(Button b) {
		b.getAllStyles().setBgTransparency(255);
		b.getAllStyles().setBgColor(ColorUtil.BLUE);
		b.getAllStyles().setFgColor(ColorUtil.LTGRAY);
		b.getAllStyles().setPadding(TOP, 1);
  		b.getAllStyles().setPadding(BOTTOM, 1);
	}
	
	public void createKeyListeners() {
		addKeyListener('a', accelerateCommand);
		addKeyListener('b', brakeCommand);
		addKeyListener('l', turnLeftCommand);
		addKeyListener('r', turnRightCommand);
		addKeyListener('c', collideNPRCommand);
		addKeyListener('e', collideESCommand);
		addKeyListener('g', collideDroneCommand);
		addKeyListener('t', tickCommand);
		
	}
	
	public void initializeCommands() {
		accelerateCommand = new AccelerateCommand(gw);
		turnLeftCommand = new TurnLeftCommand(gw);
		changeStratsCommand = new ChangeStratsCommand(gw);
		brakeCommand = new BrakeCommand(gw);
		turnRightCommand = new TurnRightCommand(gw);
		collideNPRCommand = new CollideNPRCommand(gw);
		collideBaseCommand = new CollideBaseCommand(gw);
		collideESCommand = new CollideESCommand(gw);
		collideDroneCommand = new CollideDroneCommand(gw);
		tickCommand = new TickCommand(gw);	
	}
	
	@SuppressWarnings("rawtypes")
	private void play() {
		// accepts keyboard commands from the player
		// and invokes appropriate methods in GameWorld to manipulate and display
		// the data and tgame state values in the game model
		
		/*Label myLabel=new Label("Enter a Command:");
		this.addComponent(myLabel);
		final TextField myTextField=new TextField();
		this.addComponent(myTextField);
		this.show();
		 
		myTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
		
				String sCommand=myTextField.getText().toString();
				myTextField.clear();
				boolean exit = false;
				switch (sCommand.charAt(0)) {
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
		); //addActionListener */
				
	}
}
