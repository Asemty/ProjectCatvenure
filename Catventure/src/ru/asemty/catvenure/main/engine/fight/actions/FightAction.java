package ru.asemty.catvenure.main.engine.fight.actions;

import ru.asemty.catvenure.main.engine.fight.Fighter;

public abstract class FightAction {
	public static enum PossibleTargetEnum {
		self,male,range,massively,all
	}
	public PossibleTargetEnum pte;
	public String name;
	public String description;
	public FightAction(String name,String description,PossibleTargetEnum pte){
		this.name=name;
		this.description=description;
		this.pte=pte;
	}
	
	public abstract void action(Fighter actor,Fighter targets);
}
