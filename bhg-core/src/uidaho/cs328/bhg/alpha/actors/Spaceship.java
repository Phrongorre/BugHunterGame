package uidaho.cs328.bhg.alpha.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Spaceship extends BaseActor
{

	public Spaceship(float x, float y, Stage s)
	{
		super(x, y, s);
		
		this.loadTexture("spaceship.png");
		this.setBoundaryPolygon(8);
		
		this.setAcceleration(150f);
		this.setMaxSpeed(300f);
		this.setDeceleration(1f);
	}
	
	@Override
	public void act(float dt)
	{
		super.act(dt);
		
		float degreesPerSecond = 200f; //rotation speed
		if (Gdx.input.isKeyPressed(Keys.LEFT))
		{
			this.rotateBy(degreesPerSecond * dt);
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			this.rotateBy(-degreesPerSecond * dt);
		}
		if (Gdx.input.isKeyPressed(Keys.UP))
		{
			this.accelerateAtAngle(this.getRotation());
		}
		
		this.applyPhysics(dt);
		this.wrapAroundWorld();
	}
	
}
