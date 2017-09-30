package HUD;

import Screen.GameScreen;

public class PauseButton extends HudButton
{

	public PauseButton(HUD hud, int index)
	{
		super(hud, index);
	}
	
	@Override
	public void click()
	{
		hud.pause();
	}

}
