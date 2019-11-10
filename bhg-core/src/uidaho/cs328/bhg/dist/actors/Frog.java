package uidaho.cs328.bhg.dist.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Frog extends BaseActor
{
	
	private Shield shield;
	private float moveSpeed;
	private float shotsPerSecond = 2f;
	private float shootRefresh;
	private float croaksPerSecond = 1f;
	private float croakRefresh;
	private float warpsPerSecond = 1f;
	private float warpRefresh;
	
	private int shieldPower = 3;
	
	private Sound croak;
	
	public Frog(float x, float y, Stage s)
	{
		super(x, y, s);
		
		this.loadTexture("frog.png");
		FileHandle fh = Gdx.files.internal("sounds/croak.ogg");
		this.croak = Gdx.audio.newSound(fh);
		this.setBoundaryPolygon(8);
		
		this.rotateBy(90f);
		
		this.shield = new Shield(s);
		this.addActor(this.shield);
		this.shield.centerAtPosition(this.getWidth()/2, this.getHeight()/2);
		this.moveSpeed = 250f;
		this.shootRefresh = 1f/this.shotsPerSecond;
		this.croakRefresh = 1f/this.croaksPerSecond;
		this.warpRefresh = 1f/this.warpsPerSecond;
	}
	
	@Override
	public void act(float dt)
	{
		super.act(dt);

		this.shootRefresh += dt;
		this.croakRefresh += dt;
		this.warpRefresh += dt;
		
		if (Gdx.input.isKeyPressed(Keys.LEFT))
		{
			this.moveBy(-this.moveSpeed * dt, 0f);
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			this.moveBy( this.moveSpeed * dt, 0f);
		}
		
		this.shield.setOpacity(this.shieldPower / 3f);
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
		
		if (this.shootRefresh < 1f/this.shotsPerSecond) { return; }
		
		Missile missile = new Missile(this.getStage());
		missile.centerAtActor(this);
		missile.setRotation(this.getRotation());
		missile.setMotionAngle(this.getRotation());
		
		this.shootRefresh = 0f;
	}
	
	public void croak()
	{
		if (this.getStage() == null) { return; }
		
		if (this.croakRefresh < 1f/this.croaksPerSecond) { return; }
		
		Croak croak = new Croak(this.getStage());
		croak.centerAtActor(this);
		croak.setRotation(this.getRotation());
		croak.setMotionAngle(this.getRotation());
		
		this.croak.play(1.00f);
		
		this.croakRefresh = 0f;
	}
	
	public void warp()
	{
		if (this.getStage() == null) { return; }
		
		if (this.warpRefresh < 1f/this.warpsPerSecond) { return; }
		
		Warp warp1 = new Warp(this.getStage());
		warp1.centerAtActor(this);
		this.setPosition(MathUtils.random(800f), this.getY());
		
		Warp warp2 = new Warp(this.getStage());
		warp2.centerAtActor(this);
		
		this.warpRefresh = 0f;
	}
	
	public boolean hit(int damage)
	{
		if (this.shieldPower <= 0) { return true; }
		this.shieldPower -= damage;
		if (this.shieldPower < 0) { this.shieldPower = 0; }
		return false;
	}
	
}
