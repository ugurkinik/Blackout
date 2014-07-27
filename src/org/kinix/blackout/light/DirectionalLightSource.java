package org.kinix.blackout.light;

import org.kinix.blackout.Global;

import box2dLight.ConeLight;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;


public class DirectionalLightSource extends BaseLightSource
{
	float angle;
	
	public DirectionalLightSource(float distance, int x, int y, float direction, float angle,
			float r, float g, float b, boolean turnedOn)
	{
		super(distance, x, y, r, g, b);
		
		this.angle = direction;
		
		light = new ConeLight(Global.rayHandler, (int)(distance*Global.lightQuality*angle/360),
				new Color(r, g, b, 1f), distance, 0, 0, direction, angle/2);
				
		light.setDistance(distance);
		light.setSoft(true); 			// to make edges as visible.
		light.setSoftnessLength(32);	// to set visibility distance for edges.
										// (should be smaller size of object)
		

		light.attachToBody(body, 0, 0.5f);
		body.setTransform(body.getPosition(), direction*MathUtils.degRad);
		
		light.setActive(turnedOn);
	}
	
	@Override
	public void setTransform(Vector2 position, float rotation)
	{
		Vector2 curPosition = body.getPosition();
		
		float dx = position.x - curPosition.x;
		float dy = position.y - curPosition.y;
		
		if(dx!=0 || dy!=0)
		{
			angle = MathUtils.atan2(dy, dx);
		}
		
		
		body.setTransform(position, angle);
	}
	
	
	@Override
	public void render()
	{
		final Vector2 position = body.getPosition();
		// TODO: use directional sprite or rotate sprite
		Global.batch.draw(Global.light, position.x - 16, position.y - 16, 32, 32);
	}
	

}
