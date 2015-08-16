package ru.asemty.catvenure.main.engine.mapobjects;

import java.awt.Point;
import java.util.ArrayList;

import ru.asemty.catvenure.main.engine.Engine;
import ru.asemty.catvenure.main.engine.Treasure;
import ru.asemty.catvenure.main.engine.enemy.Enemis;
import ru.asemty.catvenure.main.engine.enemy.Enemy;

public class EnemyParty implements IMapObject {
	public ArrayList<Enemy> enemis;
	public Treasure treasure;

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
	public EnemyParty copy(){
		EnemyParty copy = new EnemyParty();
		for(Enemy e:enemis){
			copy.enemis.add(Enemis.copy(e));
		}
		return copy;
	}
	public EnemyParty addEnemy(Enemy enemy){
		enemis.add(enemy);
		return this;
	}
	
	@Override
	public void interact() {
		for(int i=0;i<25;i++){
			for(int j=0;j<20;j++){
				if(Engine.dungeon.getCurrentStage().objects[i][j]==this){
					Engine.goToFight(this,new Point(i,j));
				}
			}
		}
		
	}
}
