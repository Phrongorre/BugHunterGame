package uidaho.cs328.bhg.alpha;

import uidaho.cs328.bhg.alpha.screens.LevelScreen;

public class BugHunterAlpha extends BaseGame
{
	
	@Override
	public void create()
	{
		super.create();
		BaseGame.setActiveScreen(new LevelScreen());
	}
	
}
