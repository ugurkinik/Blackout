package org.kinix.blackout;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

public class InGameControl implements InputProcessor
{
	Map map;
	
	public InGameControl(Map map)
	{
		this.map = map;
	}

	@Override
	public boolean keyDown(int keycode)
	{
		switch(keycode)
		{
		case Keys.W:
			map.player.walk(0,1);
			break;
		case Keys.A:
			map.player.walk(-1,0);
			break;
		case Keys.S:
			map.player.walk(0,-1);
			break;
		case Keys.D:
			map.player.walk(1,0);
			break;
		case Keys.E:
			map.takeLight();
			break;
		case Keys.Q:
			map.player.turnOnOffLight();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		switch(keycode)
		{
		case Keys.W:
			map.player.walk(0,-1);
			break;
		case Keys.A:
			map.player.walk(1,0);
			break;
		case Keys.S:
			map.player.walk(0,1);
			break;
		case Keys.D:
			map.player.walk(-1,0);
			break;
		}
		
		return false;
	}

	@Override
	public boolean keyTyped(char character)
	{

		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{

		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{

		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{

		return false;
	}

}
