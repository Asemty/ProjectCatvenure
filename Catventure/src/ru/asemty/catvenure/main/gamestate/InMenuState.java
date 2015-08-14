package ru.asemty.catvenure.main.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import ru.asemty.catvenure.main.GameConst;
import ru.asemty.catvenure.main.engine.Engine;
import lib.Sprites;

public class InMenuState implements IGameState{

	@Override
	public void Draw(Graphics gr) {
		gr.drawImage(Sprites.getImage("titleBackGround"), 0, 0, null);
		gr.setColor(Color.black);
		gr.setFont(new Font("calibri", Font.BOLD, 50));
		gr.drawString("Start", GameConst.screenWidth/2-100, 300);
	}

	@Override
	public void KeyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			Engine.goToVillage();
		}
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
	}

	@Override
	public void Step() {
		// TODO Auto-generated method stub
		
	}

}
