package uidaho.cs328.bhg.dist;

import uidaho.cs328.bhg.dist.screens.MenuScreen;

public class BugHunter extends BaseGame
{
	
	@Override
	public void create()
	{
		super.create();
		BaseGame.setActiveScreen(new MenuScreen());
	}
	
}
