package org.kinix.blackout;

import java.util.ArrayList;

import box2dLight.Light;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

public class Map
{
	ArrayList<LightSource> lights;
	ArrayList<Block> blocks;
	ArrayList<Wall> walls;
	ArrayList<Glass> glasses;

	Player player;

	BitmapFont font;

	public Map()
	{
		lights = new ArrayList<LightSource>();
		blocks = new ArrayList<Block>();
		walls = new ArrayList<Wall>();
		glasses = new ArrayList<Glass>();

		// TODO: do not load textures if they are already loaded
		Global.light = new Texture(Gdx.files.internal("light.png"));
		Global.box = new Texture(Gdx.files.internal("box.jpg"));
		Global.floor = new Texture(Gdx.files.internal("floor.png"));
		Global.player = new Texture(Gdx.files.internal("player.png"));
		Global.wall = new Texture(Gdx.files.internal("wall.png"));
		Global.glass = new Texture(Gdx.files.internal("glass.jpg"));

		font = new BitmapFont();
		font.setScale(2);

		lights.add(new LightSource(300, 300, 250, 560, 0.5f, 0.5f, 0.5f));
		lights.add(new LightSource(300, 200, 450, 260, 0.5f, 0.5f, 0.5f));
		lights.add(new LightSource(300, 360, 650, 360, 0.5f, 0.5f, 0.5f));

		blocks.add(new Block(400, 280));
		blocks.add(new Block(500, 580));
		blocks.add(new Block(580, 580));
		blocks.add(new Block(640, 580));
		blocks.add(new Block(840, 380));
		blocks.add(new Block(740, 300));
		

		walls.add(new Wall(200, 200, 200, 200));
		walls.add(new Wall(650, 200, 200, 200));
		glasses.add(new Glass(500, 200, 50, 200));

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

		for (Block block : blocks)
		{
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
		for (int i = 0; i < 1280; i += Global.floor.getWidth())
		{
			for (int j = 0; j < 720; j += Global.floor.getHeight())
			{
				Global.batch.draw(Global.floor, i, j);
			}
		}

	}
	
	
	public boolean takeLight()
	{
		if(player.isMovingLight())
		{
			player.dropLight();
			return true;
		}
		
		float distanceSqr;
		Vector2 playerPos = player.body.getPosition();
		
		for(LightSource light:lights)
		{
			Vector2 lightPos = light.body.getPosition();
			distanceSqr = 0;
			distanceSqr += (lightPos.x - playerPos.x)*(lightPos.x - playerPos.x);
			distanceSqr += (lightPos.y - playerPos.y)*(lightPos.y - playerPos.y);
			
			if(distanceSqr < 16*16)
			{
				player.takeLight(light);
				return true;
			}
		}
		
		return false;
	}
}