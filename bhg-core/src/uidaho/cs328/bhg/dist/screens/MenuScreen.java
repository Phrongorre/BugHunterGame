package uidaho.cs328.bhg.dist.screens;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import uidaho.cs328.bhg.dist.BaseGame;
import uidaho.cs328.bhg.dist.actors.BaseActor;

public class MenuScreen extends BaseScreen
{
	
	@Override
	public void initialize()
	{
		BaseActor space = new BaseActor(0f, 0f, this.mainStage);
		space.loadTexture("space.png");
		space.setSize(800f, 600f);
		BaseActor.setWorldBounds(space);
		
		BaseActor title = new BaseActor(0f, 0f, this.uiStage);
		title.loadTexture("gui/space_frog.png");
		title.centerAtPosition(400f, 300f);
		
		BaseActor startInfo = new BaseActor(0f, 0f, this.uiStage);
		startInfo.loadTexture("gui/press_space.png");
		startInfo.centerAtPosition(350f, 100f);
		
		Action blinkSequence = Actions.sequence(
			Actions.fadeOut(1f),
			Actions.fadeIn(1f)
		);
		
		startInfo.addAction(Actions.forever(blinkSequence));
	}
	
	@Override
	public void update(float dt)
	{ }
	
	@Override
	public boolean keyDown(int keycode)
	{
		switch (keycode)
		{
		case Keys.SPACE:
			BaseGame.setActiveScreen(new ControlsScreen());
			return true;
		default:
			break;
		}
		
		return false;
	}
	
}
