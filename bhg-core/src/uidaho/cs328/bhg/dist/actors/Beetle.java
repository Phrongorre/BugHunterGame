package uidaho.cs328.bhg.dist.actors;

import java.util.Random;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Beetle extends BaseActor
{
	
	private float lifetime;
	private float speed = 100f;
	private float eccentricity;
	
	public Beetle(float x, float y, Stage s)
	{
		super(x, y, s);
		
		this.loadAnimationFromSheet("beetle.png", 1, 2, 0.05f, true);
		
		this.setSpeed(this.speed);
		this.setMaxSpeed(10*this.speed);
		this.setDeceleration(0f);
		
		this.lifetime = 0f;
		Random rand = new Random();
		this.eccentricity = rand.nextFloat() * 3 * this.speed;
	}
	
	@Override
	public void act(float dt)
	{
		super.act(dt);
		
		//Sine wave motion
		this.lifetime += dt;
		float dx = MathUtils.sin(this.lifetime * MathUtils.PI2) * this.eccentricity;
		Vector2 motionVec = new Vector2(dx, -this.speed);
		this.setSpeed(motionVec.len());
		this.setMotionAngle(motionVec.angle());
		
		this.applyPhysics(dt);
		this.wrapAroundWorld();
	}
	
	public void stun()
	{
		this.moveBy(0f, 100f);
	}
	
}
