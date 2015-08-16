package ru.asemty.catvenure.main.engine.enemy;

import ru.asemty.catvenure.main.engine.fight.Fighter;

public class Enemy extends Fighter{
	public String name;
	public String sprite;
	public Enemy(String name,String sprite){
		super();
		this.name=name;
		this.sprite=sprite;
	}

}
