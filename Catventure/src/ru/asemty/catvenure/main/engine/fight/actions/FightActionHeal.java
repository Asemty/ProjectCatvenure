package ru.asemty.catvenure.main.engine.fight.actions;

import ru.asemty.catvenure.main.engine.fight.AttackSourse;
import ru.asemty.catvenure.main.engine.fight.Fighter;

public class FightActionHeal extends FightAction{

	@Override
	public void action(Fighter actor, Fighter targets) {
		if(targets!=null){
			targets.attacked(new AttackSourse(actor, "heal", -actor.wisdom*3));
		}
	}

	@Override
	public PossibleTargetEnum getPossibleTarget() {
		// TODO Auto-generated method stub
		return PossibleTargetEnum.friendly;
	}
	
	@Override
	public String getName(){
		return "Heal";
	}
	
	@Override
	public String getDescription(){
		return "Holy touch for your friend";
	}

}
