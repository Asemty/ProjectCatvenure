package ru.asemty.catvenure.main.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import lib.Sprites;
import ru.asemty.catvenure.main.engine.Engine;
import ru.asemty.catvenure.main.engine.Items;

public class InPotionShopState implements IGameState{

	@Override
	public void Draw(Graphics gr) {
		gr.drawImage(Sprites.getImage("potionShopBackGround"), 0, 0, null);
		gr.setFont(new Font("calibri", Font.BOLD, 70));
		gr.setColor(Color.yellow);
		gr.drawString(Engine.party.gold+" G", 600, 500);
	}

	@Override
	public void KeyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
			Engine.goToVillage();
		}
		if(e.getKeyCode()==KeyEvent.VK_H){
			if(Engine.party.gold>=5){
				Engine.party.gold-=5;
				Engine.party.bag.add(Items.healPotion);
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_M){
			if(Engine.party.gold>=10){
				Engine.party.gold-=10;
				Engine.party.bag.add(Items.manaPotion);
			}
		}
	}

	@Override
	public void Step() {
		// TODO Auto-generated method stub
		
	}

}
