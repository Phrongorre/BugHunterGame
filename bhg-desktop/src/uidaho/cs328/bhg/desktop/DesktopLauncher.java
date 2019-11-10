package uidaho.cs328.bhg.desktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import uidaho.cs328.bhg.alpha.BugHunterAlpha;
import uidaho.cs328.bhg.beta.BugHunterBeta;
import uidaho.cs328.bhg.dist.BugHunter;

public class DesktopLauncher
{
	
	private static final int ALPHA_BUILD = -2;
	private static final int BETA_BUILD  = -1;
	private static final int DIST_BUILD  =  0;
	
	private static final int BUILD_VERSION = BETA_BUILD;
	
	public static void main (String[] args)
	{
		Game myGame;
		
		switch (BUILD_VERSION)
		{
		case ALPHA_BUILD:
			myGame = new BugHunterAlpha();
		case BETA_BUILD:
			myGame = new BugHunterBeta();
		default:
		case DIST_BUILD:
			myGame = new BugHunter();
		}
		
		new LwjglApplication(myGame, "Bug Hunter", 800, 600);
	}
	
}
