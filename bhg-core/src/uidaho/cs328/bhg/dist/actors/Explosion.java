package uidaho.cs328.bhg.dist.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Explosion extends BaseActor
{
	
	public Explosion(Stage s)
	{
		super(0f, 0f, s);
		
		this.loadAnimationFromSheet("explosion.png", 6, 6, 0.03f, false);
	}
	
	@Override
	public void act(float dt)
	{
		super.act(dt);
		
		if (this.isAnimationFinished())
		{
			this.remove();
		}
	}
	
}
