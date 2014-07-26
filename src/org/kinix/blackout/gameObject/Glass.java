package org.kinix.blackout.gameObject;

import org.kinix.blackout.Global;

import com.badlogic.gdx.math.Vector2;

public class Glass extends BaseObject
{

	public Glass(int x, int y, int width, int height)
	{
		super(x, y, width, height, false, false, true);
	}

	@Override
	public void render()
	{
		final Vector2 position = body.getPosition();

		Global.batch.setColor(1, 1, 1, 0.7f);	// For transparency
		Global.batch.draw(Global.glass, position.x -width/2, position.y -height/2, 0, 0, width, height);			
		Global.batch.setColor(1, 1, 1, 1);
	}
}
