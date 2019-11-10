package uidaho.cs328.bhg.dist.screens;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;

import uidaho.cs328.bhg.dist.BaseGame;
import uidaho.cs328.bhg.dist.actors.BaseActor;

public class CreditsScreen extends BaseScreen
{
	
	private BaseActor credits;
	
	@Override
	public void initialize()
	{
		BaseActor space = new BaseActor(0f, 0f, this.mainStage);
		space.loadTexture("space.png");
		space.setSize(800f, 600f);
		BaseActor.setWorldBounds(space);
		
		this.credits = new BaseActor(0f, 0f, this.uiStage);
		this.credits.loadTexture("gui/credits.png");
		this.credits.setPosition(400f, -50f, Align.top);
	}
	
	@Override
	public void update(float dt)
	{
		this.credits.moveBy(0f, 100f * dt);
		
		if (this.credits.getY(Align.bottom) > this.mainStage.getHeight() + 50f)
		{
			BaseGame.setActiveScreen(new MenuScreen());
		}
	}
	
	@Override
	public boolean keyDown(int keycode)
	{
		switch (keycode)
		{
		case Keys.SPACE:
			BaseGame.setActiveScreen(new LevelScreen());
			return true;
		default:
			break;
		}
		
		return false;
	}
	
}
