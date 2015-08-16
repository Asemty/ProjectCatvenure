package ru.asemty.catvenure.main.engine.enemy;

import ru.asemty.catvenure.main.engine.fight.Fighter;

public class Enemy extends Fighter {
	public String name;
	public String sprite;

	public Enemy(String name, String sprite) {
		super();
		this.name = name;
		this.sprite = sprite;
	}

	public Enemy setHp(int h) {
		this.mhp = h;
		this.hp = mhp;
		return this;
	}

	public Enemy setMp(int m) {
		this.mmp = m;
		this.mp = mhp;
		return this;
	}
	
	public Enemy setStats(int c,int w,int v) {
		this.cunning = c;
		this.wisdom = w;
		this.vim = v;
		return this;
	}
}
