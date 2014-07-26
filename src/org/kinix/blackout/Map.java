package org.kinix.blackout;

import java.util.ArrayList;

import org.kinix.blackout.gameObject.Box;
import org.kinix.blackout.gameObject.MovableObject;
import org.kinix.blackout.gameObject.Glass;
import org.kinix.blackout.gameObject.Wall;
import org.kinix.blackout.light.LightSource;

import box2dLight.Light;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

public class Map
{
	ArrayList<LightSource> lights;
	ArrayList<MovableObject> blocks;
	ArrayList<Wall> walls;
	ArrayList<Glass> glasses;

	Player player;

	BitmapFont font;

	public Map()
	{

		lights = new ArrayList<LightSource>();
		blocks = new ArrayList<MovableObject>();
		walls = new ArrayList<Wall>();
		glasses = new ArrayList<Glass>();

		// TODO: do not load textures if they are already loaded
		Global.light = new Texture(Gdx.files.internal("img/light.png"));
		Global.box = new Texture(Gdx.files.internal("img/box.jpg"));
		Global.floor = new Texture(Gdx.files.internal("img/floor.png"));
		Global.player = new Texture(Gdx.files.internal("img/player.png"));
		Global.wall = new Texture(Gdx.files.internal("img/wall.png"));
		Global.glass = new Texture(Gdx.files.internal("img/glass.jpg"));

		Global.floor.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		Global.wall.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		Global.glass.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

		font = new BitmapFont();
		font.setScale(2);

		lights.add(new LightSource(300, 1280, 720, 0.5f, 0.5f, 0.5f));
		lights.add(new LightSource(300, 0, 0, 0.5f, 0.5f, 0.5f));
		lights.add(new LightSource(300, 450, 260, 0.5f, 0.5f, 0.5f));
		lights.add(new LightSource(400, 650, 360, 0.5f, 0.5f, 0.5f));

		blocks.add(new Box(432, 168));

		walls.add(new Wall(350, 232, 32, 32));
		glasses.add(new Glass(382, 200, 32, 32));

		player = new Player(400, 200);

		Light.setContactFilter(Global.F_OPAQUE, Global.F_OPAQUE, Global.F_OPAQUE);

		Gdx.input.setInputProcessor(new InGameControl(this));

	}

	public void dispose()
	{
		Global.light.dispose();
		Global.box.dispose();
		Global.floor.dispose();
		Global.player.dispose();
		Global.wall.dispose();
	}

	long time;

	public void render()
	{
		time = System.currentTimeMillis();

		drawFloor();

		Global.batch.enableBlending();

		for (LightSource light : lights)
		{
			light.render();
		}

		for (MovableObject block : blocks)
		{
			block.stopIfNotPushed();
			block.render();
		}

		for (Wall wall : walls)
		{
			wall.render();
		}

		for (Glass glass : glasses)
		{
			glass.render();
		}

		player.render();

	}

	public void drawHud()
	{
		if (player.isDark())
		{
			font.setColor(Color.RED);
			font.draw(Global.batch, "DARK", 0, 720);
		}
		else
		{
			font.setColor(Color.GREEN);
			font.draw(Global.batch, "LIGHT", 0, 720);
		}

	}

	public void drawFloor()
	{
		Global.batch.draw(Global.floor, 0, 0, 0, 0, 1280,720);
	}

	public boolean takeLight()
	{
		if (player.isMovingLight())
		{
			player.dropLight();
			return true;
		}

		float distanceSqr;
		Vector2 playerPos = player.getPosition();

		for (LightSource light : lights)
		{
			Vector2 lightPos = light.getPosition();
			distanceSqr = 0;
			distanceSqr += (lightPos.x - playerPos.x) * (lightPos.x - playerPos.x);
			distanceSqr += (lightPos.y - playerPos.y) * (lightPos.y - playerPos.y);

			if (distanceSqr < 16 * 16)
			{
				player.takeLight(light);
				return true;
			}
		}

		return false;
	}
}