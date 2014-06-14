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

		lights.add(new LightSource(300, 600, 500, 270, 1, 1, 1));

		blocks.add(new Block(570, 300));
		blocks.add(new Block(370, 350));
		blocks.add(new Block(470, 200));
		blocks.add(new Block(670, 150));
		blocks.add(new Block(400, 450));
		blocks.add(new Block(780, 410));

		player = new Player(400, 300);

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
			font.draw(Global.batch, "DARK", 0, 600);
		}
		else
		{
			font.setColor(Color.GREEN);
			font.draw(Global.batch, "LIGHT", 0, 600);
		}
		
		font.setColor(Color.YELLOW);
		font.draw(Global.batch, "Calculation time per frame: "+(System.currentTimeMillis()-time) +" ms", 0, 30);
	}

	public void drawFloor()
	{
		for (int i = 0; i < 1067; i += Global.floor.getWidth())
		{
			for (int j = 0; j < 600; j += Global.floor.getHeight())
			{
				Global.batch.draw(Global.floor, i, j);
			}
		}

	}
}
