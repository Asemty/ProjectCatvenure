package ru.asemty.catvenure.main.engine;

import java.awt.Point;

import ru.asemty.catvenure.main.engine.enemy.Enemis;
import ru.asemty.catvenure.main.engine.mapobjects.EnemyParty;
import ru.asemty.catvenure.main.engine.mapobjects.IMapObject;
import ru.asemty.catvenure.main.engine.mapobjects.Stairs;

public class Stage {
	public boolean[][] passability;
	public IMapObject[][] objects;
	public Point catPatyPos;

	public Stage() {
		passability = new boolean[25][20];
		objects = new IMapObject[25][20];
	}

	public static Stage generate() {
		Stage result = new Stage();
		for (int i = 0; i < 25; i++) {
			result.passability[i][10] = true;
		}
		result.objects[4][10] = new EnemyParty()
		.addEnemy(Enemis.copy(Enemis.beholder))
		.addEnemy(Enemis.copy(Enemis.beholder))
		.addEnemy(Enemis.copy(Enemis.beholder))
		.addEnemy(Enemis.copy(Enemis.beholder),4);
		result.objects[24][10] = new Stairs();
		result.catPatyPos= new Point(15,10);
		return result;
	}
}
