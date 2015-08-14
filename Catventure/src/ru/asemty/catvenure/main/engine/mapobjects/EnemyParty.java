package ru.asemty.catvenure.main.engine.mapobjects;

import java.util.ArrayList;

import ru.asemty.catvenure.main.engine.Engine;
import ru.asemty.catvenure.main.engine.enemy.Enemy;

public class EnemyParty implements IMapObject {
	public ArrayList<Enemy> enemis;

	public EnemyParty() {
		enemis = new ArrayList<Enemy>();
	}
	public String getSprite(){
		if(enemis.size()>0){
			return enemis.get(0).sprite+"Icon";
		}else{
			return "";
		}
	}
	public EnemyParty addEnemy(Enemy enemy){
		enemis.add(enemy);
		return this;
	}
	@Override
	public void interact() {
		Engine.goToFight(this);
	}
}
