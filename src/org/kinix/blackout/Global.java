package org.kinix.blackout;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.RayHandler;

public class Global
{
	public static RayHandler rayHandler;
	public static World world;
	public static SpriteBatch batch;
	public static OrthographicCamera camera;
}
