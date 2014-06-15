package org.kinix.blackout;

import java.awt.Font;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Map
{
	ArrayList<LightSource> lights;
	ArrayList<Block> blocks;

	Player player;

	BitmapFont font;

	public Map()
	{
		lights = new ArrayList<LightSource>();
		blocks = new ArrayList<Block>();

		// TODO: do not load textures if they are already loaded
		Global.light = new Texture(Gdx.files.internal("light.png"));
		Global.box = new Texture(Gdx.files.internal("box.jpg"));
		Global.floor = new Texture(Gdx.files.internal("floor.png"));
		Global.player = new Texture(Gdx.files.internal("player.png"));
		
		font = new BitmapFont();
		font.setScale(2);

		lights.add(new LightSource(300, 600, 650, 360, 1, 1, 1));

		blocks.add(new Block(400, 280));
		blocks.add(new Block(500, 580));
		blocks.add(new Block(580, 580));
		blocks.add(new Block(640, 580));
		blocks.add(new Block(840, 380));
		blocks.add(new Block(740, 300));

		player = new Player(400, 200);

		Global.setShadowFilter((short) 42);

		Gdx.input.setInputProcessor(new InGameControl(this));

	}

	public void dispose()
	{
		Global.light.dispose();
		Global.box.dispose();
		Global.floor.dispose();
		Global.player.dispose();
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
		
		font.setColor(Color.YELLOW);
		font.draw(Global.batch, "Calculation time per frame: "+(System.currentTimeMillis()-time) +" ms", 0, 30);
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
}
