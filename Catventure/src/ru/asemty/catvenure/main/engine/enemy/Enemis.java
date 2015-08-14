package ru.asemty.catvenure.main.engine.enemy;

public class Enemis {

	public static Enemy beholder = new Enemy("Beholder", "beholder");
	public static Enemy copy(Enemy enemy){
		Enemy copy= new Enemy(enemy.name,enemy.sprite);
		return copy;
	}
}
