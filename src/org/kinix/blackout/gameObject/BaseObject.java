package org.kinix.blackout.gameObject;

import org.kinix.blackout.Global;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class BaseObject
{
	protected Body body;
	protected int width, height;

	public BaseObject(int x, int y, boolean dynamic, boolean opaque, boolean solid)
	{
		// if size does not specified, set size as 32x32.
		this(x, y, 32, 32, dynamic, opaque, solid);
	}
	
	public BaseObject(int x, int y, int width, int height, boolean dynamic,
			boolean opaque, boolean solid)
	{
		FixtureDef def = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width/2, height/2);
		def.shape = shape;
		def.density = 21f;

		def.filter.categoryBits = 0;
		def.filter.maskBits = 0;

		if (opaque)
		{
			def.filter.categoryBits |= Global.F_OPAQUE;
			def.filter.maskBits |= Global.F_OPAQUE;
		}

		if (solid)
		{
			def.filter.categoryBits |= Global.F_SOLID;
			def.filter.maskBits |= Global.F_SOLID;
		}

		BodyDef boxBodyDef = new BodyDef();

		if (dynamic)
		{
			boxBodyDef.type = BodyType.DynamicBody;
			boxBodyDef.linearDamping = 100;
		}
		else
		{
			boxBodyDef.type = BodyType.StaticBody;
		}
		
		boxBodyDef.position.x = x+width/2;
		boxBodyDef.position.y = y-height/2;
		body = Global.world.createBody(boxBodyDef);
		body.createFixture(def);
		shape.dispose();
		
		body.setFixedRotation(true);	// Objects are not rotating when they are pushed from corner.

		this.width = width;
		this.height = height;
	}
	
	
	public Vector2 getPosition()
	{
		return body.getPosition();
	}

	
	public void render()
	{
		final Vector2 position = body.getPosition();
		Global.batch.draw(Global.wall, position.x -width/2, position.y -height/2, 0, 0, width, height);			
	}
}
