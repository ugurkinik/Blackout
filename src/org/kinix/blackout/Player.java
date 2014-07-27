package org.kinix.blackout;

import org.kinix.blackout.gameObject.BaseObject;
import org.kinix.blackout.light.BaseLightSource;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Transform;

public class Player extends BaseObject
{
	private float speed;
	private float vx,vy;
	private BaseLightSource light;
	
	public Player(int x, int y)
	{
		super(x, y, true, false, true);
			
		speed = 250;
	}
	
	
	
	public void render()
	{
		final Vector2 position = body.getPosition();
		Global.batch.draw(Global.player, position.x - 16, position.y - 16, 32, 32);
		
		body.setLinearVelocity(vx*speed, vy*speed);
		

		if(light != null)
		{			
			Transform transform = body.getTransform();
			light.setTransform(transform.getPosition(), transform.getRotation());

		}
	}
	
	public void stop()
	{
		vx = 0;
		vy = 0;
	}
	
	public void walk(float vx, float vy)
	{
		this.vx += vx;
		this.vy += vy;
	}
	
	
	public boolean isDark()
	{
		return Global.rayHandler.pointAtShadow(body.getPosition().x, body.getPosition().y);
	}
	
	public void takeLight(BaseLightSource light)
	{
		this.light = light;
	}
	
	public boolean isMovingLight()
	{
		if(light == null)
			return false;
		
		return true;
	}
	
	public void dropLight()
	{
		light.stop();	// to avoid light movement after dropping.
		light = null;
	}

}
