package org.kinix.blackout;

import box2dLight.Light;
import box2dLight.PointLight;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class LightSource
{
	Light light;
	int rays;
	float distance;
	Body body;
	float r,g,b;
	
	public LightSource(int rays, float distance, int x, int y, float r, float g, float b)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		
		light = new PointLight(Global.rayHandler, rays);
		light.setDistance(distance);
		light.setSoft(true);
		light.setSoftnessLength(32);
		light.setColor(new Color(r,g,b, 1));
		
		this.distance = distance;
		
		FixtureDef def = new FixtureDef();
		def.restitution = 0.9f;
		def.friction = 0.01f;
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(32, 32);
		def.shape = shape;
		def.density = 1f;
		def.isSensor = true;
		BodyDef boxBodyDef = new BodyDef();
		boxBodyDef.type = BodyType.DynamicBody;
		boxBodyDef.position.x = x;
		boxBodyDef.position.y = y;
		body = Global.world.createBody(boxBodyDef);
		body.createFixture(def);
		shape.dispose();
		
		light.attachToBody(body, 0, 0.5f);
		

	}
	
	public void render()
	{
		final Vector2 position = body.getPosition();
		Global.batch.draw(Global.light, position.x - 16, position.y - 16, 32, 32);
		
	}
	

}
