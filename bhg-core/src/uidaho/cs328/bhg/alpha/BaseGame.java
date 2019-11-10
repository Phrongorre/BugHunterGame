package uidaho.cs328.bhg.alpha;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

import uidaho.cs328.bhg.alpha.screens.BaseScreen;

public abstract class BaseGame extends Game
{
	
	private static BaseGame game;
	
	public BaseGame()
	{
		BaseGame.game = this;
	}
	
	@Override
	public void create()
	{
		//prepare for multiple classes/stages to receive discrete input
		InputMultiplexer im = new InputMultiplexer();
		Gdx.input.setInputProcessor(im);
	}
	
	public static void setActiveScreen(BaseScreen s)
	{
		BaseGame.game.setScreen(s);
	}

}
