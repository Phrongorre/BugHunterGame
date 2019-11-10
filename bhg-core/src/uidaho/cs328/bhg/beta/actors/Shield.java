package uidaho.cs328.bhg.beta.actors;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Shield extends BaseActor
{

	public Shield(float x, float y, Stage s)
	{
		super(x, y, s);
		
		this.loadTexture("shields.png");
		
		Action pulse = Actions.sequence(
				Actions.scaleTo(1.05f, 1.05f, 1f),
				Actions.scaleTo(0.95f, 0.95f, 1f)
				);
		
		this.addAction(Actions.forever(pulse));
	}
	
}
