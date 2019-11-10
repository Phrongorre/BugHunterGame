package uidaho.cs328.bhg.beta.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Laser extends BaseActor
{
	
	public Laser(float x, float y, Stage s)
	{
		super(x, y, s);
		
		this.loadTexture("laser.png");
		
		this.addAction(Actions.delay(1f));
		this.addAction(Actions.after(Actions.fadeOut(0.5f)));
		this.addAction(Actions.after(Actions.removeActor()));
		
		this.setSpeed(400f);
		this.setMaxSpeed(500f);
		this.setDeceleration(0f);
	}
	
	@Override
	public void act(float dt)
	{
		super.act(dt);
		
		this.applyPhysics(dt);
		this.wrapAroundWorld();
	}
	
}
