package uidaho.cs328.bhg.beta.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Spaceship extends BaseActor
{
	
	private Thrusters thrusters;
	private Shield shield;
	public int shieldPower;
	
	public Spaceship(float x, float y, Stage s)
	{
		super(x, y, s);
		
		this.loadTexture("spaceship.png");
		this.setBoundaryPolygon(8);
		
		this.setAcceleration(150f);
		this.setMaxSpeed(300f);
		this.setDeceleration(2f);
		
		this.thrusters = new Thrusters(0f, 0f, s);
		this.addActor(this.thrusters);
		this.thrusters.setPosition(-this.thrusters.getWidth(), this.getHeight()/2 - this.thrusters.getHeight()/2);
		this.shield = new Shield(0f, 0f, s);
		this.addActor(this.shield);
		this.shield.centerAtPosition(this.getWidth()/2, this.getHeight()/2);
		this.shieldPower = 100;
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
			this.thrusters.setVisible(true);
		}
		else
		{
			this.thrusters.setVisible(false);
		}
		
		this.shield.setOpacity(this.shieldPower / 100f);
		if (this.shieldPower <= 0f)
		{
			this.shield.setVisible(false);
		}
		
		this.applyPhysics(dt);
		this.wrapAroundWorld();
	}
	
	public void shoot()
	{
		if (this.getStage() == null) { return; }
		
		Laser laser = new Laser(0f, 0f, this.getStage());
		laser.centerAtActor(this);
		laser.setRotation(this.getRotation());
		laser.setMotionAngle(this.getRotation());
	}
	
	public void warp()
	{
		if (this.getStage() == null) { return; }
		
		Warp warp1 = new Warp(0f, 0f, this.getStage());
		warp1.centerAtActor(this);
		this.setPosition(MathUtils.random(800f), MathUtils.random(600f));
		
		Warp warp2 = new Warp(0f, 0f, this.getStage());
		warp2.centerAtActor(this);
	}
	
}
