package org.kinix.blackout;

import box2dLight.Light;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Player
{
	Body body;
	private float speed;
	
	public Player(int x, int y)
	{
		FixtureDef def = new FixtureDef();
		def.restitution = 0.9f;
		def.friction = 0.01f;
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(16, 16);
		def.shape = shape;
		BodyDef boxBodyDef = new BodyDef();
		boxBodyDef.type = BodyType.DynamicBody;
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
	}
	
	public void stay()
	{
		body.setLinearVelocity(0, 0);
	}
	
	public void walk(float vx, float vy)
	{
		body.setLinearVelocity(vx*speed, vy*speed);
	}
	
	
	public boolean isDark()
	{
		return Global.rayHandler.pointAtShadow(body.getPosition().x, body.getPosition().y);
	}

}
