package com.brentPaterson.roboTrack;
import com.brentPaterson.roboTrack.Commands.AboutCommand;
import com.brentPaterson.roboTrack.Commands.AccelerateCommand;
import com.brentPaterson.roboTrack.Commands.BrakeCommand;
import com.brentPaterson.roboTrack.Commands.ChangeStratsCommand;
import com.brentPaterson.roboTrack.Commands.ExitCommand;
import com.brentPaterson.roboTrack.Commands.HelpCommand;
import com.brentPaterson.roboTrack.Commands.PauseCommand;
import com.brentPaterson.roboTrack.Commands.SoundToggleCommand;
import com.brentPaterson.roboTrack.Commands.TurnLeftCommand;
import com.brentPaterson.roboTrack.Commands.TurnRightCommand;
import com.brentPaterson.roboTrack.Sounds.BGSound;
import com.brentPaterson.roboTrack.Sounds.Sound;
import com.brentPaterson.roboTrack.Views.MapView;
import com.brentPaterson.roboTrack.Views.ScoreView;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.Toolbar; 


public class Game extends Form implements Runnable {
	
	// static game values
	private static float rangeX;
	private static float rangeY;
	
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private Container westContainer, eastContainer, southContainer;
	private Button accelerateButton, turnLeft, turnRight, changeStrats, 
			brake, pause;
	
	// commands
	private AccelerateCommand accelerateCommand;
	private BrakeCommand brakeCommand;
	private ChangeStratsCommand changeStratsCommand;
	private TurnLeftCommand turnLeftCommand;
	private TurnRightCommand turnRightCommand;
	private HelpCommand helpCommand;
	private SoundToggleCommand soundToggleCommand;
	private AboutCommand aboutCommand;
	private ExitCommand exitCommand;
	private PauseCommand pauseCommand;
	
	// tick stuff
	private UITimer timer;
	private boolean paused;
	private BGSound bgsound;
	
	public Game() {
		gw = new GameWorld();
		mv = new MapView(gw);
		sv = new ScoreView(gw);
		
		// create border layout
		System.out.println("Creating BorderLayout");
		this.setLayout(new BorderLayout());
		
		initializeCommands();
		
		/*********************************************************
		 * 
		 * Containers
		 * 
		 *********************************************************/
		
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
		
		/*********************************************************
		 * 
		 * Buttons && key bindings
		 * 
		 *********************************************************/
		
		createButtons();
				
		westContainer.add(accelerateButton);
		westContainer.add(turnLeft);
		westContainer.add(changeStrats);
		eastContainer.add(brake);
		eastContainer.add(turnRight);
		southContainer.add(pause);

		
		createKeyListeners();
		
		
		/*********************************************************
		 * 
		 * Toolbar
		 * 
		 *********************************************************/
		Toolbar myToolbar = new Toolbar();
		myToolbar.setUIID("mainToolbar");
		myToolbar.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLUE));
		this.setToolbar(myToolbar);	
		myToolbar.setTitle("Robo-Track");
		
		// side menu commands
		myToolbar.addCommandToSideMenu(accelerateCommand);
		myToolbar.addCommandToSideMenu(aboutCommand);
		myToolbar.addCommandToSideMenu(exitCommand);
		
			// check box for sound, on side menu
		CheckBox soundCheckBox = new CheckBox("Sound toggle");
		soundCheckBox.getAllStyles().setBgTransparency(255);
		soundCheckBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		soundCheckBox.setCommand(soundToggleCommand);
		myToolbar.addComponentToSideMenu(soundCheckBox);
		
		// other commands
		myToolbar.addCommandToRightBar(helpCommand);
		
		/*********************************************************
		 * 
		 * Implementing containers, intializing
		 * 
		 *********************************************************/
		System.out.println("Adding mapview and scoreview");
		this.add(BorderLayout.CENTER, mv);
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.WEST, westContainer);
		this.add(BorderLayout.SOUTH, southContainer);
		this.add(BorderLayout.EAST, eastContainer);
		
		/*********************************************************
		 * 
		 * Tick timer in place here, 20ms ticks
		 * 
		 *********************************************************/
		
		timer = new UITimer(this);
		timer.schedule(gw.getTickRate(), true, this); 
		paused = false;
		bgsound = new BGSound("Milkomeda.wav");
		//bgsound.play();
		
		this.show();
		rangeX = mv.getWidth();
		rangeY = mv.getHeight();
		System.out.println("Map View Resolution: " + rangeX + "x" + rangeY);
		
		gw.init();
	}
	
	@Override
	public void run() {
		gw.tick();
		
	}
	
	/*********************************************************
	 * 
	 * Helper Functions
	 * 
	 *********************************************************/
	
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
		pause = new Button("Pause");

		
		setButtonCommands();
		
		setButtonStyles(accelerateButton);
		setButtonStyles(turnLeft);
		setButtonStyles(changeStrats);
		setButtonStyles(brake);
		setButtonStyles(turnRight);
		setButtonStyles(pause);

	}
	
	public void setButtonCommands() {
		accelerateButton.setCommand(accelerateCommand);
		turnLeft.setCommand(turnLeftCommand);
		changeStrats.setCommand(changeStratsCommand);
		brake.setCommand(brakeCommand);
		turnRight.setCommand(turnRightCommand);
		pause.setCommand(pauseCommand);
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
		
	}
	
	public void initializeCommands() {
		accelerateCommand = new AccelerateCommand(gw);
		turnLeftCommand = new TurnLeftCommand(gw);
		changeStratsCommand = new ChangeStratsCommand(gw);
		brakeCommand = new BrakeCommand(gw);
		turnRightCommand = new TurnRightCommand(gw);
		helpCommand = new HelpCommand(gw);
		soundToggleCommand = new SoundToggleCommand(gw);
		aboutCommand = new AboutCommand(gw);
		exitCommand = new ExitCommand(gw);
		pauseCommand = new PauseCommand(gw);
	}

	public static float[] getMapResolution() {
		return new float[] {rangeX, rangeY};
	}
	
	public void timerToggle() {
		if (!paused) {
			//pause game
			paused = !paused;
			
		}
	}


}
