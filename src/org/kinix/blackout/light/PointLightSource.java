package org.kinix.blackout.light;

import org.kinix.blackout.Global;

import box2dLight.PointLight;

import com.badlogic.gdx.graphics.Color;

public class PointLightSource extends BaseLightSource
{
	public PointLightSource(float distance, int x, int y, float r, float g, float b, boolean turnedOn)
	{
		super(distance, x, y, r, g, b);
		
		light = new PointLight(Global.rayHandler, (int) (distance * Global.lightQuality));
		light.setColor(new Color(r, g, b, 1));
		this.distance = distance;

		light.setDistance(distance);
		light.setSoft(true); 			// to make edges as visible.
		light.setSoftnessLength(32);	// to set visibility distance for edges.
										// (should be smaller size of object)

		light.attachToBody(body, 0, 0.5f);
		light.setActive(turnedOn);
	}
}
