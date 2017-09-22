package com.mygdx.game.desktop;

import Game.Config;
import Game.Core;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = true;
		config.title = Config.GAME_TITLE;
		config.width = (int) Config.GAME_WIDTH;
		config.height = (int) Config.GAME_HEIGHT;
		config.foregroundFPS = 60;
		new LwjglApplication(new Core(), config);
	}
}
