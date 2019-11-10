package uidaho.cs328.bhg.dist.actors;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Croak extends BaseActor
{
	
	public Croak(Stage s)
	{
		super(0f, 0f, s);
		
		this.loadTexture("croak.png");
		
		Action croakSequence = Actions.sequence(
			//Delay 1 second
			Actions.delay(1f),
			//Fade out over 2 seconds
			Actions.fadeOut(2f),
			//Remove croak from screen
			Actions.removeActor()
		);
		this.addAction(croakSequence);
		
		this.setSpeed(100f);
		this.setAcceleration(100f);
		this.setMaxSpeed(500f);
		this.setDeceleration(0f);
	}
	
	@Override
	public void act(float dt)
	{
		super.act(dt);
		
		this.accelerateForward();
		this.applyPhysics(dt);
	}
	
}
