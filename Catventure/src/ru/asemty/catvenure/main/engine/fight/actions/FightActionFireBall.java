package ru.asemty.catvenure.main.engine.fight.actions;

import ru.asemty.catvenure.main.engine.fight.AttackSourse;
import ru.asemty.catvenure.main.engine.fight.Fighter;

public class FightActionFireBall extends FightAction{

	@Override
	public void action(Fighter actor, Fighter targets) {
		if(targets!=null){
			targets.attacked(new AttackSourse(actor, "fire", actor.wisdom*5));
		}
	}

	@Override
	public PossibleTargetEnum getPossibleTarget() {
		// TODO Auto-generated method stub
		return PossibleTargetEnum.range;
	}
	
	@Override
	public String getName(){
		return "Fire ball";
	}
	
	@Override
	public String getDescription(){
		return "Throw a ball of fury at the enemy!";
	}

}
