package zelda;

import javax.swing.*;

import sun.text.resources.cldr.ps.FormatData_ps;
import zelda.engine.Game;

import java.util.Locale;
import java.util.ResourceBundle;

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
			setSize(575, 445);
			//Debug code will be updated in time.
		}

		setSize(515, 415); // Code is now functional
		setLocationRelativeTo(null); // Set to the center of the screen relative to x,y getSize.
		setTitle(" TriforceRoyale ");
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		view = new View(game, this);
		ctl = new Controller(game, view, this);
	}

	public static void main(String[] args)
	{
		CheatDetection.onCheatDetect();

		final JFrame parent = new JFrame();
		String name = JOptionPane.showInputDialog(parent,
				"What is your name?", null);

		// Start the osDetection Class function and return a value for the OS type.
		osDetection.getOperatingSystemType();
	}

	public static void RunGame() {
		new Main();
	}
}