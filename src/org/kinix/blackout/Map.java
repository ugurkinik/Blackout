package org.kinix.blackout;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Map
{
	ArrayList<LightSource> lights;
	ArrayList<Block> blocks;
	private Texture box;
	private Texture light;
	private Texture floor;
	
	public Map()
	{
		lights = new ArrayList<LightSource>();
		blocks = new ArrayList<Block>();
		
		
		light = new Texture(Gdx.files.internal("light.png"));
		box = new Texture(Gdx.files.internal("box.jpg"));		
		floor = new Texture(Gdx.files.internal("floor.png"));
		
		
		lights.add(new LightSource(360, 300, 700, 350, 1f,0.3f,0.3f));
		lights.add(new LightSource(360, 500, 200, 250, 0.3f, 1f, 0.3f));
		
		lights.get(1).body.setLinearVelocity(30, 0);
		
		blocks.add(new Block(470,300));
		blocks.add(new Block(270,350));
		blocks.add(new Block(370,200));
		blocks.add(new Block(570,150));
		blocks.add(new Block(300,450));
		blocks.add(new Block(680,410));
		
	}
	
	public void dispose()
	{
		light.dispose();
		box.dispose();
		floor.dispose();
	}
	
	public void render()
	{		
		drawFloor();
		
		Global.batch.enableBlending();
		
		for(LightSource light:lights)
		{
			light.render();
		}
		
		
		for(Block block:blocks)
		{
			block.render();
		}
	}
	
	
	public void drawFloor()
	{
		for(int i=0; i<800; i+= floor.getWidth())
		{
			for(int j=0; j<600; j+= floor.getHeight())
			{
				Global.batch.draw(floor, i, j);
			}
		}
			
	}
}
