package uidaho.cs328.bhg.beta.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Warp extends BaseActor
{

	public Warp(float x, float y, Stage s)
	{
		super(x, y, s);
		
		this.loadAnimationFromSheet("warp.png", 4, 8, 0.05f, true);
		
		this.addAction(Actions.delay(1f));
		this.addAction(Actions.after(Actions.fadeOut(0.5f)));
		this.addAction(Actions.after(Actions.removeActor()));
	}
	
}
