package uidaho.cs328.bhg.beta;

import uidaho.cs328.bhg.beta.screens.LevelScreen;

public class BugHunterBeta extends BaseGame
{
	
	@Override
	public void create()
	{
		super.create();
		BaseGame.setActiveScreen(new LevelScreen());
	}
	
}
