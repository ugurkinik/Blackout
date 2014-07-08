package org.kinix.blackout;

import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public class Global
{
	public static final short F_OPAQUE = 1;	// ...001
	public static final short F_SOLID = 2; // ...010
	
	public static RayHandler rayHandler;
	public static World world;
	public static SpriteBatch batch;
	public static OrthographicCamera camera;
	
	
	public static Texture box;
	public static Texture light;
	public static Texture floor;
	public static Texture player;
	public static Texture wall;
	public static Texture glass;
	

}
