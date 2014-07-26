package org.kinix.blackout.light;

import org.kinix.blackout.Global;
import org.kinix.blackout.gameObject.BaseObject;

import box2dLight.Light;
import box2dLight.PointLight;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class LightSource extends BaseObject
{
	protected Light light;
	protected float distance;
	protected float r,g,b;
	
	public LightSource(float distance, int x, int y, float r, float g, float b)
	{
		super(x, y, true, false, false);
		
		this.r = r;
		this.g = g;
		this.b = b;
		
		light = new PointLight(Global.rayHandler, (int) (distance));
		light.setDistance(distance);
		light.setSoft(true);			// to make edges as visible.
		light.setSoftnessLength(32);	// to set visibility distance for edges. (should be smaller size of object)
		light.setColor(new Color(r,g,b, 1));
		
		this.distance = distance;

		light.attachToBody(body, 0, 0.5f);
	}
	
	public Vector2 getPosition()
	{
		return body.getPosition();
	}
	
	public void setTransform(Vector2 position, float rotation)
	{
		body.setTransform(position, rotation);
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
	

}
