package uidaho.cs328.bhg.beta.screens;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import uidaho.cs328.bhg.beta.actors.BaseActor;
import uidaho.cs328.bhg.beta.actors.Explosion;
import uidaho.cs328.bhg.beta.actors.Laser;
import uidaho.cs328.bhg.beta.actors.Rock;
import uidaho.cs328.bhg.beta.actors.Spaceship;

public class LevelScreen extends BaseScreen
{
	
	private Spaceship spaceship;
	private boolean gameOver;
	
	@Override
	public void initialize()
	{
		BaseActor space = new BaseActor(0f, 0f, this.mainStage);
		space.loadTexture("space.png");
		space.setSize(800f, 600f);
		BaseActor.setWorldBounds(space);
		
		this.spaceship = new Spaceship(400f, 300f, this.mainStage);
		this.gameOver = false;
		
		new Rock(600f, 500f, this.mainStage);
		new Rock(600f, 300f, this.mainStage);
		new Rock(600f, 100f, this.mainStage);
		new Rock(400f, 100f, this.mainStage);
		new Rock(200f, 100f, this.mainStage);
		new Rock(200f, 300f, this.mainStage);
		new Rock(200f, 500f, this.mainStage);
		new Rock(400f, 500f, this.mainStage);
	}
	
	@Override
	public void update(float dt)
	{
		for (BaseActor rockActor : BaseActor.getList(this.mainStage, Rock.class.getName()))
		{
			if (rockActor.overlaps(this.spaceship))
			{
				if (this.spaceship.shieldPower <= 0f)
				{
					Explosion boom = new Explosion(0f, 0f, this.mainStage);
					boom.centerAtActor(this.spaceship);
					this.spaceship.remove();
					this.spaceship.setPosition(-1000f, -1000f);
					
					BaseActor messageLose = new BaseActor(0f, 0f, this.uiStage);
					messageLose.loadTexture("message-lose.png");
					messageLose.centerAtPosition(400f, 300f);
					messageLose.setOpacity(0f);
					messageLose.addAction(Actions.fadeIn(1f));
					this.gameOver = true;
				}
				else
				{
					this.spaceship.shieldPower -= 34f;
					Explosion boom = new Explosion(0f, 0f, this.mainStage);
					boom.centerAtActor(rockActor);
					rockActor.remove();
				}
			}
			
			for (BaseActor laserActor : BaseActor.getList(this.mainStage, Laser.class.getName()))
			{
				if (laserActor.overlaps(rockActor))
				{
					Explosion boom = new Explosion(0f, 0f, this.mainStage);
					boom.centerAtActor(rockActor);
					laserActor.remove();
					rockActor.remove();
				}
			}
		}
		
		if (!this.gameOver && BaseActor.count(this.mainStage, Rock.class.getName()) == 0)
		{
			BaseActor messageWin = new BaseActor(0f, 0f, this.uiStage);
			messageWin.loadTexture("message-win.png");
			messageWin.centerAtPosition(400f, 300f);
			messageWin.setOpacity(0f);
			messageWin.addAction(Actions.fadeIn(1f));
			this.gameOver = true;
		}
	}
	
	//override default InputProcessor method
	@Override
	public boolean keyDown(int keycode)
	{
		if (keycode == Keys.SPACE)
		{
			this.spaceship.shoot();
		}
		if (keycode == Keys.X)
		{
			this.spaceship.warp();
		}
		
		return false;
	}
	
}
