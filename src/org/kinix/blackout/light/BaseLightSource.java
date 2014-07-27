package org.kinix.blackout.light;

import org.kinix.blackout.Global;
import org.kinix.blackout.gameObject.BaseObject;

import box2dLight.Light;

import com.badlogic.gdx.math.Vector2;

public class BaseLightSource extends BaseObject
{
	protected Light light;
	protected float distance;
	protected float r,g,b;
	
	public BaseLightSource(float distance, int x, int y, float r, float g, float b)
	{
		super(x, y, true, false, false);
		
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public Vector2 getPosition()
	{
		return body.getPosition();
	}
	
	public void setTransform(Vector2 position, float rotation)
	{
		body.setTransform(position, 0);
	}
	
	
	public void stop()
	{
		body.setLinearVelocity(0,0);
	}
	
	public void render()
	{
		final Vector2 position = body.getPosition();
		Global.batch.draw(Global.light, position.x - 16, position.y - 16, 32, 32);	
	}
	
	public void turnOff()
	{
		light.setActive(false);
	}
	
	
	public void turnOn()
	{
		light.setActive(true);
	}
	
	public void turnOnOff()
	{
		light.setActive(!light.isActive());
	}

}
