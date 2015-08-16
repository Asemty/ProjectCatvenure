package ru.asemty.catvenure.main.engine.enemy;

public class Enemis {

	public static Enemy beholder = new Enemy("Beholder", "beholder").setHp(14).setStats(2, 5, 2);
	public static Enemy copy(Enemy enemy){
		Enemy copy= new Enemy(enemy.name,enemy.sprite);
		copy.setHp(enemy.mhp).setMp(enemy.mmp);
		copy.cunning=enemy.cunning;
		copy.wisdom=enemy.wisdom;
		copy.vim=enemy.vim;
		return copy;
	}
}
