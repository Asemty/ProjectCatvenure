package ru.asemty.catvenure.main.engine.enemy;

import java.util.ArrayList;

import ru.asemty.catvenure.main.engine.fight.actions.FightAction;

public class Enemy {
	public int hp, mp, cunning, wisdom, vim;
	public ArrayList<FightAction> actions = new ArrayList<FightAction>();
	public String name;
	public String sprite;

	public Enemy(String name, String sprite) {
		super();
		this.name = name;
		this.sprite = sprite;
	}

	public Enemy setStats(int c,int w,int v) {
		this.cunning = c;
		this.wisdom = w;
		this.vim = v;
		return this;
	}
	
	public Enemy setHp(int h) {
		this.hp = h;
		return this;
	}

	public Enemy setMp(int m) {
		this.mp = m;
		return this;
	}
	
	
}
