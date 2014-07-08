package org.kinix.blackout;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Wall
{
	Body body;
	int w, h;

	public Wall(int x, int y, int w, int h)
	{
		this.w = w;
		this.h = h;
		
		FixtureDef def = new FixtureDef();
		def.restitution = 0;
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(w/2, h/2);
		def.shape = shape;
		def.density = 100f;
		def.filter.categoryBits = Global.F_OPAQUE | Global.F_SOLID;
		def.filter.maskBits = Global.F_OPAQUE | Global.F_SOLID;
		BodyDef boxBodyDef = new BodyDef();
		boxBodyDef.type = BodyType.StaticBody;
		boxBodyDef.position.x = x;
		boxBodyDef.position.y = y;	
		body = Global.world.createBody(boxBodyDef);
		body.createFixture(def);
		shape.dispose();
				
	}
	
	
	public void render()
	{
		final Vector2 position = body.getPosition();
		
		Global.batch.draw(Global.wall, position.x -w/2, position.y -h/2, 0, 0, w, h);			
	}
}
