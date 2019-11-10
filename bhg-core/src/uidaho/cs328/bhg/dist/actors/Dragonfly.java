package uidaho.cs328.bhg.dist.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Dragonfly extends BaseActor
{
	
	private BaseActor target;
	
	private int state;
	private Animation<TextureRegion> passiveAnim;
	private Animation<TextureRegion> aggroAnim;
	
	public Dragonfly(float x, float y, Stage s, BaseActor t)
	{
		super(x, y, s);
		
		this.passiveAnim = this.loadAnimationFromSheet("dragonfly_passive.png",    1, 2, 0.01f, true);
		this.aggroAnim   = this.loadAnimationFromSheet("dragonfly_aggressive.png", 1, 2, 0.01f, true);

		this.setAnimation(this.passiveAnim);
		
		this.setAcceleration(50f);
		this.setMaxSpeed(200f);
		this.setDeceleration(0f);
		
		this.target = t;
	}
	
	@Override
	public void act(float dt)
	{
		super.act(dt);
	
		switch (this.state)
		{
		default:
		case 0:
			//Passive behavior
			//Do nothing
			break;
		case 1:
			//Aggressive behavior
			//Chase player
			//this.setMotionAngle(MathUtils.atan2(this.));
			float angle = MathUtils.atan2(this.target.getY()-this.getY(), this.target.getX()-this.getX()) * 180f / MathUtils.PI;
			this.accelerateAtAngle(angle);
			break;
		}
		
		this.applyPhysics(dt);
		this.wrapAroundWorld();
	}
	
	
	public void hit()
	{
		switch (this.state)
		{
		case 0:
			this.state++;
			this.setAnimation(this.aggroAnim);
			break;
		default:
		case 1:
			this.remove();
			break;
		}
	}
	
	public void stun()
	{
		if (this.state > 0)
		{
			this.moveBy(0f, 100f);
		}
	}
	
}
