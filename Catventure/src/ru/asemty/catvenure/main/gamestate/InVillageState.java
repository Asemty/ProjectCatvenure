package ru.asemty.catvenure.main.gamestate;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import ru.asemty.catvenure.main.engine.Engine;
import lib.Sprites;

public class InVillageState implements IGameState{

	public void drawBuild(Graphics gr,int x,int y,String buildName,String text){
		gr.drawImage(Sprites.getImage("buildBack"), x, y+50, null);
		gr.drawImage(Sprites.getImage(buildName), x, y, null);
		if(!buildName.equals(""))gr.drawString(text, x, y+100);
	}
	
	@Override
	public void Draw(Graphics gr) {
		gr.drawImage(Sprites.getImage("villageBackGround"), 0, 0, null);
		drawBuild(gr,100,350,"dungeon","Press d");
		drawBuild(gr,600,450,"castle","Press c");
		drawBuild(gr,600,150,Engine.Village.potionShop?"potionShop":"","press p");
	}

	@Override
	public void KeyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_P){
			Engine.goToPotionShop();
		}
		if(e.getKeyCode()==KeyEvent.VK_C){
			Engine.goToCastle();
		}
		if(e.getKeyCode()==KeyEvent.VK_D){
			Engine.goToDungeon();
		}
	}

	@Override
	public void Step() {
		// TODO Auto-generated method stub
		
	}

}
