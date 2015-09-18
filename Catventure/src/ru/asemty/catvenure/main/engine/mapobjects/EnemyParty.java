package ru.asemty.catvenure.main.engine.mapobjects;

import java.awt.Point;

import ru.asemty.catvenure.main.engine.Engine;
import ru.asemty.catvenure.main.engine.Treasure;
import ru.asemty.catvenure.main.engine.enemy.Enemis;
import ru.asemty.catvenure.main.engine.enemy.Enemy;

public class EnemyParty implements IMapObject {
	public Enemy[] enemis;
	public Treasure treasure;

	public EnemyParty() {
		enemis = new Enemy[9];
	}

	public String getSprite() {

		for (int i = 0; i < 9; i++) {
			if (enemis[i] != null && !enemis[0].sprite.equals("")) {
				return enemis[i].sprite + "Icon";
			}
		}
		return "";

	}

	public EnemyParty copy() {
		EnemyParty copy = new EnemyParty();
		for (int i = 0; i < 9; i++) {
			copy.enemis[i]=Enemis.copy(enemis[i]);
		}
		return copy;
	}

	public EnemyParty addEnemy(Enemy enemy,int i) {
		enemis[i]=enemy;
		return this;
	}
	public EnemyParty addEnemy(Enemy enemy) {
		for(int i=0;i<9;i++){
			if(enemis[i]==null){
				return this.addEnemy(enemy, i);
			}
		}
		return this;

	}
	@Override
	public void interact() {
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 20; j++) {
				if (Engine.dungeon.getCurrentStage().objects[i][j] == this) {
					Engine.goToFight(this, new Point(i, j));
				}
			}
		}

	}
}
