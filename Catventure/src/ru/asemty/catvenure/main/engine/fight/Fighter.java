package ru.asemty.catvenure.main.engine.fight;


public abstract class Fighter {
	public int cunning, wisdom, vim, hp, mp,mhp,mmp;
	public Fighter(){
	}
	
	public boolean isDead(){
		return mhp<=0;
	}
	
}
