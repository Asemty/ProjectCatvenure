package ru.asemty.catvenure.main.gamestate;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

import lib.Sprites;
import ru.asemty.catvenure.main.engine.Cat;
import ru.asemty.catvenure.main.engine.Engine;
import ru.asemty.catvenure.main.engine.EquipableItem;
import ru.asemty.catvenure.main.engine.Item;
import ru.asemty.catvenure.main.engine.enemy.Enemy;
import ru.asemty.catvenure.main.engine.mapobjects.EnemyParty;

public class InFightState implements IGameState{

	EnemyParty enemyParty;
	Point enemyPartyLocation;
	public InFightState(EnemyParty enemyParty,Point enemyLoc) {
		this.enemyParty=enemyParty;
		this.enemyPartyLocation=enemyLoc;
	}

	@Override
	public void Draw(Graphics gr) {
		gr.drawImage(Sprites.getImage("fightBackGround"), 0, 0, null);
		for(int i=0;i<6;i++){
			if(i<enemyParty.enemis.size()){
				gr.drawImage(Sprites.getImage(enemyParty.enemis.get(i).sprite), 60+170*(i/3)-50*i/3, 260+100*(i%3), null);
			}
			if(i<Engine.party.cats.length && Engine.party.cats[i]!=null){
				int x=610+170*(i/3)-50*i/3;
				int y=260+100*(i%3);
				gr.drawImage(Sprites.getImage("cat"), x, y, null);
				for(Item item :Engine.party.cats[i].bag){
					if(item instanceof EquipableItem){
						gr.drawImage(Sprites.getImage(((EquipableItem) item).sprite), x, y, null);
					}
				}
			}
		}
	}

	@Override
	public void KeyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
			Engine.goToVillage();
		}
	}

	@Override
	public void Step() {
		// TODO Auto-generated method stub
		
	}
	
	public void nextTurn(){
		
	}
	
	public void fightEnd(boolean win){
		Engine.goToVillage();
	}
	
	public boolean checkCatIsLive(){
		for(Cat cat: Engine.party.cats){
			if(!cat.isDead()){
				return true;
			}
		}
		fightEnd(false);
		return false;
	}
	public boolean checkEnemyIsLive(){
		for(Enemy enemy: enemyParty.enemis){
			if(!enemy.isDead()){
				return true;
			}
		}
		fightEnd(true);
		return false;
	}

}
