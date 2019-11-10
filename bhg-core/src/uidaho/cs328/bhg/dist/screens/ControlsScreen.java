package uidaho.cs328.bhg.dist.screens;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import uidaho.cs328.bhg.dist.BaseGame;
import uidaho.cs328.bhg.dist.actors.BaseActor;

public class ControlsScreen extends BaseScreen
{
	
	@Override
	public void initialize()
	{
		BaseActor space = new BaseActor(0f, 0f, this.mainStage);
		space.loadTexture("space.png");
		space.setSize(800f, 600f);
		BaseActor.setWorldBounds(space);
		
		BaseActor movementInfo = new BaseActor(0f, 0f, this.uiStage);
		movementInfo.loadTexture("gui/movement.png");
		movementInfo.centerAtPosition(400f, 450f);
		
		BaseActor actionsInfo = new BaseActor(0f, 0f, this.uiStage);
		actionsInfo.loadTexture("gui/actions.png");
		actionsInfo.centerAtPosition(400f, 250f);
		
		BaseActor startInfo = new BaseActor(0f, 0f, this.uiStage);
		startInfo.loadTexture("gui/press_space.png");
		startInfo.setOpacity(0f);
		startInfo.centerAtPosition(400f, 100f);
		
		Action blinkSequence = Actions.sequence(
			Actions.fadeOut(1f),
			Actions.fadeIn(1f)
		);
		
		startInfo.addAction(Actions.delay(2f, Actions.fadeIn(1f)));
		startInfo.addAction(Actions.after(Actions.forever(blinkSequence)));
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
			BaseGame.setActiveScreen(new LevelScreen());
			return true;
		default:
			break;
		}
		
		return false;
	}
	
}
