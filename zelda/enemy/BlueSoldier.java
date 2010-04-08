package zelda.enemy;

import zelda.collision.Hittable;
import zelda.collision.Weapon;
import java.awt.Rectangle;
import java.util.HashMap;
import zelda.engine.Game;
import zelda.karacter.Direction;
import zelda.karacter.Karacter;

/**
 * A Blue soldier.
 *
 * @author maartenhus
 */
public class BlueSoldier extends Karacter implements Hittable
{
	private Behavior behavior;
	private long inputInterval = 50;
	private long lastInput = System.currentTimeMillis();

	public BlueSoldier(Game game, int x, int y, Direction direction, int ticks)
	{
		super(game, x, y, 10, 20, Direction.DOWN, "images/blue-soldier.png");

		spriteLoc.put("Stand right",	new Rectangle(0, 0, 27, 27));
		spriteLoc.put("Walk right 1",	new Rectangle(30, 0, 32, 27));
		spriteLoc.put("Walk right 2",	new Rectangle(64, 0, 29, 27));

		spriteLoc.put("Stand left",		new Rectangle(0, 30, 27, 27));
		spriteLoc.put("Walk left 1",	new Rectangle(30, 30, 32, 27));
		spriteLoc.put("Walk left 2",	new Rectangle(64, 30, 29, 27));

		spriteLoc.put("Stand up",		new Rectangle(0, 60, 22, 27));
		spriteLoc.put("Walk up 1",		new Rectangle(30, 60, 22, 26));
		spriteLoc.put("Walk up 2",		new Rectangle(64, 57, 22, 33));

		spriteLoc.put("Stand down",		new Rectangle(0, 90, 22, 33));
		spriteLoc.put("Walk down 1",	new Rectangle(30, 90, 22, 34));
		spriteLoc.put("Walk down 2",	new Rectangle(60, 90, 22, 38));
		spriteLoc.put("Walk down 3",	new Rectangle(0, 125, 22, 35));
		
		sprite.setSprite(spriteLoc.get("Stand right"));

		this.direction = direction;

		state = new StandState(this);
		behavior = new PatrolBehavior(this, ticks);
	}

	@Override
	public void doInLoop()
	{
		if (System.currentTimeMillis() > lastInput + inputInterval)
		{
			state.handleInput();
			behavior.behave();
			lastInput = System.currentTimeMillis();
		}
	}

	public void hitBy(Weapon weapon)
	{
		switch(weapon)
		{
			case SWORD:
				alive = false;
				break;

            case BOMB:
                alive = false;
                break;
		}
	}

	public Behavior getBehavior()
	{
		return behavior;
	}

	public void setBehavior(Behavior behavior)
	{
		this.behavior = behavior;
	}

	public HashMap<String, Rectangle> getSpriteLoc()
	{
		return spriteLoc;
	}
}