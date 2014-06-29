package org.kinix.blackout;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Player
{
	public Body body;
	private float speed;
	private float vx,vy;
	private LightSource light;
	
	public Player(int x, int y)
	{
		FixtureDef def = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(16, 16);
		def.shape = shape;
		def.density = 1f;
		BodyDef boxBodyDef = new BodyDef();
		boxBodyDef.type = BodyType.KinematicBody;
		boxBodyDef.position.x = x;
		boxBodyDef.position.y = y;	
		body = Global.world.createBody(boxBodyDef);
		body.createFixture(def);
		shape.dispose();
					
		speed = 100;
	}
	
	
	public void render()
	{
		final Vector2 position = body.getPosition();
		Global.batch.draw(Global.player, position.x - 16, position.y - 16, 32, 32);
		
		body.setLinearVelocity(vx*speed, vy*speed);
		if(light != null)
			light.body.setLinearVelocity(vx*speed, vy*speed);
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
	
	public void takeLight(LightSource light)
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
		light.body.setLinearVelocity(0, 0);
		light = null;
	}

}
