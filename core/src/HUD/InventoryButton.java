package HUD;

import Screen.GameScreen;

public class InventoryButton extends HudButton
{

	public InventoryButton(HUD hud, int index)
	{
		super(hud, index);
	}
	
	@Override
	public void click()
	{
		hud.pause("Inventory", true);
	}

}
