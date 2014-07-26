package org.kinix.blackout.gameObject;

import org.kinix.blackout.Global;

import com.badlogic.gdx.math.Vector2;

public class MovableObject extends BaseObject
{
	
	public MovableObject(int x, int y, boolean opaque)
	{
		super(x, y, true, opaque, true);
	}
	
	
	public void render()
	{
		Vector2 position = body.getPosition();
		Global.batch.draw(Global.box, position.x - 16, position.y - 16, 32, 32);
	}
	
	public void stopIfNotPushed()
	{
		// This method is called in each frame.
		body.setLinearVelocity(0,0);
		// If box is being moved, the pulse will change speed.
	}

}
