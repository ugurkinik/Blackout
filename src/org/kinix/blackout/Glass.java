package org.kinix.blackout;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Glass
{
	Body body;
	int w, h;

	public Glass(int x, int y, int w, int h)
	{
		this.w = w;
		this.h = h;

		FixtureDef def = new FixtureDef();
		def.restitution = 0;
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(w / 2, h / 2);
		def.shape = shape;
		def.density = 100f;
		def.filter.categoryBits = Global.F_SOLID;
		def.filter.maskBits = Global.F_SOLID;
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
		for (int i = -w / 2; i < w / 2 - 32; i += 32)
		{
			for (int j = -h / 2; j < h / 2 - 32; j += 32)
			{
				Global.batch.draw(Global.glass, position.x + i, position.y + j);
			}
		}

	}
}
