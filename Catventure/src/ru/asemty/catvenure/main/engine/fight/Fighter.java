package ru.asemty.catvenure.main.engine.fight;

import java.util.ArrayList;

import ru.asemty.catvenure.main.engine.fight.actions.FightAction;

public abstract class Fighter {
	public int hp, mp, mhp, mmp, cunning, wisdom, vim;

	public ArrayList<FightAction> actions = new ArrayList<FightAction>();
	public Fighter() {
	}

	public boolean isDead() {
		return hp <= 0;
	}

	public int getSpeed() {
		// TODO Auto-generated method stub
		return cunning*3+wisdom-vim/2;
	}
	
	public void attacked(AttackSourse as){
		this.hp-=as.damage;
	}

}
