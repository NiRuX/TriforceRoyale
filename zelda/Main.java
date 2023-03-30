package zelda;

import zelda.engine.Game;
import javax.swing.*;

import static zelda.osDetection.getOperatingSystemType;

public class Main extends JFrame
{
	private Game game;
	private View view;
	private Controller ctl;

	public Main()
	{
		setIgnoreRepaint(true);
		game = new Game();

		if(game.isDebug())
		{
			setLocationRelativeTo(null);
			setSize(1920, 1080);
			//Debug code will be updated in time.
		}

		setSize(1920, 1080); // Code is now functional
		setLocationRelativeTo(null); // Set to the center of the screen relative to x,y getSize.
		setTitle(" TriforceRoyale " + getFrames());
		System.out.println("FPS: " + getFrames());
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		view = new View(game, this);
		ctl = new Controller(game, view, this);
	}

	public static void main(String[] args)
	{
		// Start the osDetection Class function and return a value for the OS type.
		getOperatingSystemType();
	}

	public static void RunGame() {
		new Main();
	}
}