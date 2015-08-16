package ru.asemty.catvenure.main.engine.fight.actions;

import ru.asemty.catvenure.main.engine.fight.AttackSourse;
import ru.asemty.catvenure.main.engine.fight.Fighter;

public class FightActionPush extends FightAction{

	@Override
	public void action(Fighter actor, Fighter targets) {
		if(targets!=null){
			targets.attacked(new AttackSourse(actor, "phisical", actor.vim*5));
		}
	}

	@Override
	public PossibleTargetEnum getPossibleTarget() {
		// TODO Auto-generated method stub
		return PossibleTargetEnum.male;
	}
	
	@Override
	public String getName(){
		return "Push";
	}
	
	@Override
	public String getDescription(){
		return "Push your enemy!";
	}

}
