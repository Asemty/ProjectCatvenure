package ru.asemty.catvenure.main.engine.fight.actions;

import ru.asemty.catvenure.main.engine.fight.AttackSourse;
import ru.asemty.catvenure.main.engine.fight.Fighter;

public class FightActionMeteorRain extends FightAction{

	@Override
	public void action(Fighter actor, Fighter targets) {
		if(targets!=null){
			targets.attacked(new AttackSourse(actor, "fire", actor.wisdom*3));
		}
	}

	@Override
	public PossibleTargetEnum getPossibleTarget() {
		// TODO Auto-generated method stub
		return PossibleTargetEnum.massively;
	}
	
	@Override
	public String getName(){
		return "Meteor Rain";
	}
	
	@Override
	public String getDescription(){
		return "Oh my g~~ , this is Armageddon!";
	}

}
