package uidaho.cs328.bhg.alpha.screens;

import uidaho.cs328.bhg.alpha.actors.BaseActor;
import uidaho.cs328.bhg.alpha.actors.Spaceship;

public class LevelScreen extends BaseScreen
{
	
	@Override
	public void initialize()
	{
		BaseActor space = new BaseActor(0f, 0f, this.mainStage);
		space.loadTexture("space.png");
		space.setSize(800f, 600f);
		BaseActor.setWorldBounds(space);
		
		new Spaceship(400f, 300f, this.mainStage);
	}
	
	@Override
	public void update(float dt) { }
	
}
