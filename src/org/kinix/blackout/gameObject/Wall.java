package org.kinix.blackout.gameObject;

import org.kinix.blackout.Global;

import com.badlogic.gdx.math.Vector2;

public class Wall extends BaseObject
{
	public Wall(int x, int y, int width, int height)
	{
		super(x, y, width, height, false, true, true);
	}
	
	@Override
	public void render()
	{
		final Vector2 position = body.getPosition();
		Global.batch.draw(Global.wall, position.x -width/2, position.y -height/2, 0, 0, width, height);			
	}
}
