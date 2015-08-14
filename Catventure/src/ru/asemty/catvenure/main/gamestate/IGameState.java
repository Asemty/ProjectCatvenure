package ru.asemty.catvenure.main.gamestate;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public interface IGameState {

	public void Draw(Graphics gr);
	public void KeyPressed(KeyEvent e);
	public void Step();
}
