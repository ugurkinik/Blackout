package org.kinix.blackout;

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
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(16, 16);
		def.shape = shape;
		def.density = 21f;;
		def.filter.categoryBits = Global.F_OPAQUE | Global.F_SOLID;
		def.filter.maskBits = Global.F_OPAQUE | Global.F_SOLID;
		BodyDef boxBodyDef = new BodyDef();
		boxBodyDef.type = BodyType.DynamicBody;
		boxBodyDef.position.x = x;
		boxBodyDef.position.y = y;	
		boxBodyDef.linearDamping = 100;
		body = Global.world.createBody(boxBodyDef);
		body.createFixture(def);
		shape.dispose();
				
	}
	
	
	public void render()
	{
		Vector2 position = body.getPosition();
		Global.batch.draw(Global.box, position.x - 16, position.y - 16, 32, 32);

		body.setLinearVelocity(0,0);
	}

}
