package zelda;

import javax.swing.JFrame;
import zelda.engine.Game;

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
			setSize(640, 480);
			//Debug code will be updated in time.
		}

		setSize(640, 480); // Code is now functional
		setLocationRelativeTo(null); // Set to the center of the screen relative to x,y getSize.
		setTitle(" Zelda - A Link To The Past [ * ] ");
		setUndecorated(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		view = new View(game, this);
		ctl = new Controller(game, view, this);
	}

	public static void main(String[] args)
	{
		new Main();
	}
}