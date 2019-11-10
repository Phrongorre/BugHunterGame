package uidaho.cs328.bhg.beta.actors;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Rock extends BaseActor
{
	
	public Rock(float x, float y, Stage s)
	{
		super(x, y, s);
		
		this.loadTexture("rock.png");
		
		float random = MathUtils.random(30f);
		
		this.addAction(Actions.forever(Actions.rotateBy(30f + random, 1f)));
		
		this.setSpeed(50f + random);
		this.setMaxSpeed(50f + random);
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
