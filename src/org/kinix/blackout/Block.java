package org.kinix.blackout;

import java.util.ArrayList;

import box2dLight.Light;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Block
{
	Body body;
	
	public Block(int x, int y)
	{
		FixtureDef def = new FixtureDef();
		def.restitution = 0.9f;
		def.friction = 0.01f;
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(16, 16);
		def.shape = shape;
		def.density = 1f;
		def.filter.categoryBits = 42;
		def.filter.groupIndex = 42;
		def.filter.maskBits = 42;
		BodyDef boxBodyDef = new BodyDef();
		boxBodyDef.type = BodyType.DynamicBody;
		boxBodyDef.position.x = x;
		boxBodyDef.position.y = y;	
		body = Global.world.createBody(boxBodyDef);
		body.createFixture(def);
		shape.dispose();
				
	}
	
	
	public void render()
	{
		final Vector2 position = body.getPosition();
		Global.batch.draw(Global.box, position.x - 16, position.y - 16, 32, 32);

			
	}

}
