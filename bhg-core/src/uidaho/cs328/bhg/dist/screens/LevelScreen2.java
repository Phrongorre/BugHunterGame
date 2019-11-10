package uidaho.cs328.bhg.dist.screens;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;

import uidaho.cs328.bhg.dist.BaseGame;
import uidaho.cs328.bhg.dist.actors.BaseActor;
import uidaho.cs328.bhg.dist.actors.Beetle;
import uidaho.cs328.bhg.dist.actors.Croak;
import uidaho.cs328.bhg.dist.actors.Dragonfly;
import uidaho.cs328.bhg.dist.actors.Explosion;
import uidaho.cs328.bhg.dist.actors.Frog;
import uidaho.cs328.bhg.dist.actors.Missile;

public class LevelScreen2 extends BaseScreen
{
	
	private Frog frog;
	private boolean gameOver;
	
	@Override
	public void initialize()
	{
		BaseActor space = new BaseActor(0f, 0f, this.mainStage);
		space.loadTexture("space.png");
		space.setSize(800f, 600f);
		BaseActor.setWorldBounds(space);
		
		this.frog = new Frog(0f, 0f, this.mainStage);
		this.frog.setPosition(400f, 40f, Align.bottom);
		this.gameOver = false;
		
		new Dragonfly(  0f, 500f, this.mainStage, this.frog);
		new Dragonfly(150f, 500f, this.mainStage, this.frog);
		new Dragonfly(300f, 500f, this.mainStage, this.frog);
		new Dragonfly(450f, 500f, this.mainStage, this.frog);
		new Dragonfly(600f, 500f, this.mainStage, this.frog);
		
		(new Dragonfly(  0f, 700f, this.mainStage, this.frog)).hit();
		(new Dragonfly(300f, 700f, this.mainStage, this.frog)).hit();
		(new Dragonfly(600f, 700f, this.mainStage, this.frog)).hit();
		
		new Beetle( 75f, 400f, this.mainStage);
		new Beetle(225f, 400f, this.mainStage);
		new Beetle(375f, 400f, this.mainStage);
		new Beetle(525f, 400f, this.mainStage);
		
		new Beetle(125f, 450f, this.mainStage);
		new Beetle(275f, 450f, this.mainStage);
		new Beetle(425f, 450f, this.mainStage);
		new Beetle(575f, 450f, this.mainStage);
	}
	
	@Override
	public void update(float dt)
	{
		for (BaseActor dragonflyActor : BaseActor.getList(this.mainStage, Dragonfly.class.getName()))
		{
			Dragonfly dragonfly = (Dragonfly)dragonflyActor;
			if (dragonfly.overlaps(this.frog))
			{
				if (this.frog.hit(2))
				{
					//Create an explosion at frog location
					Explosion boom = new Explosion(this.mainStage);
					boom.centerAtActor(this.frog);
					//Remove frog from its parent LevelScreen
					this.frog.remove();
					
					BaseActor messageLose = new BaseActor(0f, 0f, this.uiStage);
					messageLose.loadTexture("gui/message-lose.png");
					messageLose.centerAtPosition(400f, 300f);
					messageLose.setOpacity(0f);
					messageLose.addAction(Actions.fadeIn(1f));
					this.gameOver = true;
					
					Action toMenuScreenSequence = Actions.sequence(
						Actions.delay(3f),
						Actions.run(
							() ->
							{
								BaseGame.setActiveScreen(new MenuScreen());
							}
						)
					);
						
					this.mainStage.addAction(toMenuScreenSequence);
				}
				else
				{
					Explosion boom = new Explosion(this.mainStage);
					boom.centerAtActor(dragonfly);
					dragonfly.hit();
				}
			}
			
			for (BaseActor missileActor : BaseActor.getList(this.mainStage, Missile.class.getName()))
			{
				if (missileActor.overlaps(dragonfly))
				{
					((Missile)missileActor).destroy();
					dragonfly.hit();
				}
			}
			
			for (BaseActor croakActor : BaseActor.getList(this.mainStage, Croak.class.getName()))
			{
				if (croakActor.overlaps(dragonflyActor))
				{
					croakActor.remove();
					((Dragonfly)dragonflyActor).stun();
				}
			}
		}
		
		for (BaseActor beetleActor : BaseActor.getList(this.mainStage, Beetle.class.getName()))
		{
			if (beetleActor.overlaps(this.frog))
			{
				if (this.frog.hit(1))
				{
					//Create an explosion at frog location
					Explosion boom = new Explosion(this.mainStage);
					boom.centerAtActor(this.frog);
					//Remove frog from its parent LevelScreen
					this.frog.remove();
					//Make sure frog is no longer visible
					this.frog.setPosition(-1000f, -1000f);
					
					BaseActor messageLose = new BaseActor(0f, 0f, this.uiStage);
					messageLose.loadTexture("gui/message-lose.png");
					messageLose.centerAtPosition(400f, 300f);
					messageLose.setOpacity(0f);
					messageLose.addAction(Actions.fadeIn(1f));
					this.gameOver = true;
					
					Action toMenuScreenSequence = Actions.sequence(
						Actions.delay(3f),
						Actions.run(
							() ->
							{
								BaseGame.setActiveScreen(new MenuScreen());
							}
						)
					);
						
					this.mainStage.addAction(toMenuScreenSequence);
				}
				else
				{
					Explosion boom = new Explosion(this.mainStage);
					boom.centerAtActor(beetleActor);
					beetleActor.remove();
				}
			}
			
			for (BaseActor missileActor : BaseActor.getList(this.mainStage, Missile.class.getName()))
			{
				if (missileActor.overlaps(beetleActor))
				{
					((Missile)missileActor).destroy();
					beetleActor.remove();
				}
			}
			
			for (BaseActor croakActor : BaseActor.getList(this.mainStage, Croak.class.getName()))
			{
				if (croakActor.overlaps(beetleActor))
				{
					croakActor.remove();
					((Beetle)beetleActor).stun();
				}
			}
		}
		
		if (!this.gameOver &&
			BaseActor.count(this.mainStage, Dragonfly.class.getName()) == 0 &&
			BaseActor.count(this.mainStage, Beetle.class.getName()) == 0)
		{
			BaseActor messageWin = new BaseActor(0f, 0f, this.uiStage);
			messageWin.loadTexture("gui/message-win.png");
			messageWin.centerAtPosition(400f, 300f);
			messageWin.setOpacity(0f);
			messageWin.addAction(Actions.fadeIn(1f));
			
			Action toCreditsScreenSequence = Actions.sequence(
				Actions.delay(3f),
				Actions.run(
					() ->
					{
						BaseGame.setActiveScreen(new CreditsScreen());
					}
				)
			);
			
			this.mainStage.addAction(toCreditsScreenSequence);
			
			this.gameOver = true;
		}
	}
	
	//override default InputProcessor method
	@Override
	public boolean keyDown(int keycode)
	{
		if (keycode == Keys.SPACE)
		{
			this.frog.shoot();
		}
		if (keycode == Keys.C)
		{
			this.frog.croak();
		}
		if (keycode == Keys.X)
		{
			this.frog.warp();
		}
		
		return false;
	}
	
}
