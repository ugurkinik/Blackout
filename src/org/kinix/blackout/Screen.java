package org.kinix.blackout;

import box2dLight.RayHandler;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Screen implements ApplicationListener
{
	private Map map;

	@Override
	public void create()
	{
		Global.camera = new OrthographicCamera(1280, 720);
		Global.camera.position.set(640, 360, 0);
		Global.camera.update();

		Global.batch = new SpriteBatch();

		Global.world = new World(new Vector2(0, 0), true);
		RayHandler.setGammaCorrection(true);
		RayHandler.useDiffuseLight(true);
		Global.rayHandler = new RayHandler(Global.world);
		Global.rayHandler.setCulling(true);
		Global.rayHandler.setBlurNum(1);

		map = new Map();
	}

	@Override
	public void dispose()
	{
		Global.batch.dispose();
		map.dispose();
		Global.rayHandler.dispose();
	}

	@Override
	public void render()
	{
		Global.camera.update();
		boolean stepped = fixedStep(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Global.batch.setProjectionMatrix(Global.camera.combined);
		Global.batch.disableBlending();

		Global.batch.begin();

		map.render();

		Global.batch.end();
		

		Global.rayHandler.setCombinedMatrix(Global.camera.combined, Global.camera.position.x,
				Global.camera.position.y, Global.camera.viewportWidth * Global.camera.zoom,
				Global.camera.viewportHeight * Global.camera.zoom);

		if (stepped)
			Global.rayHandler.update();
		Global.rayHandler.render();

		
		Global.batch.begin();
		
		map.drawHud();
		Global.batch.end();

	}

	private final static int MAX_FPS = 30;
	private final static int MIN_FPS = 15;
	public final static float TIME_STEP = 1f / MAX_FPS;
	private final static float MAX_STEPS = 1f + MAX_FPS / MIN_FPS;
	private final static float MAX_TIME_PER_FRAME = TIME_STEP * MAX_STEPS;
	private final static int VELOCITY_ITERS = 6;
	private final static int POSITION_ITERS = 2;

	float physicsTimeLeft;

	private boolean fixedStep(float delta)
	{
		physicsTimeLeft += delta;
		if (physicsTimeLeft > MAX_TIME_PER_FRAME)
			physicsTimeLeft = MAX_TIME_PER_FRAME;

		boolean stepped = false;
		while (physicsTimeLeft >= TIME_STEP)
		{
			Global.world.step(TIME_STEP, VELOCITY_ITERS, POSITION_ITERS);
			physicsTimeLeft -= TIME_STEP;
			stepped = true;
		}
		return stepped;
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}
}
