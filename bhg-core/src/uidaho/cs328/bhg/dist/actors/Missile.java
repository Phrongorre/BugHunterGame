package uidaho.cs328.bhg.dist.actors;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Missile extends BaseActor
{
	
	public Missile(Stage s)
	{
		super(0f, 0f, s);
		
		this.loadTexture("missile.png");
		
		Action missileSequence = Actions.sequence(
			//Delay 3 seconds
			Actions.delay(3f),
			//Run lambda expression to place explosion
			Actions.run(
				() ->
				{
					Explosion boom = new Explosion(this.getStage());
					boom.centerAtActor(this);
				}
			),
			//Remove missile from screen
			Actions.removeActor()
		);
		this.addAction(missileSequence);
		
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
	
	public void destroy()
	{
		Explosion boom = new Explosion(this.getStage());
		boom.centerAtActor(this);
		this.remove();
	}
	
}
