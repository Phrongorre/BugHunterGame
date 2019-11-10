package uidaho.cs328.bhg.dist.actors;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Shield extends BaseActor
{

	public Shield(Stage s)
	{
		super(0f, 0f, s);
		
		this.loadTexture("shields.png");
		
		Action pulse = Actions.sequence(
			Actions.scaleTo(1.05f, 1.05f, 1f),
			Actions.scaleTo(0.95f, 0.95f, 1f)
		);
		
		this.addAction(Actions.forever(pulse));
	}
	
}
