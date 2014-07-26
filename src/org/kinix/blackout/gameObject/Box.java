package org.kinix.blackout.gameObject;

import org.kinix.blackout.Global;

import com.badlogic.gdx.math.Vector2;

public class Box extends MovableObject
{
	public Box(int x, int y)
	{
		super(x, y, true);
	}
	
	@Override
	public void render()
	{
		Vector2 position = body.getPosition();
		Global.batch.draw(Global.box, position.x - 16, position.y - 16, 32, 32);
	}
}
