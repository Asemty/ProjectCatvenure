package ru.asemty.catvenure.main.engine.fight.actions;

import ru.asemty.catvenure.main.engine.fight.AttackSourse;
import ru.asemty.catvenure.main.engine.fight.Fighter;
import ru.asemty.catvenure.main.engine.rolesystem.AttackType;

public class FightActionsList {

	public static FightAction FireBall = new FightAction("Fire ball", "Throw a ball of fury at the enemy!",
			FightAction.PossibleTargetEnum.range) {
		@Override
		public void action(Fighter actor, Fighter targets) {
			if (targets != null) {
				targets.attacked(new AttackSourse(actor,targets, AttackType.fire, actor.wisdom * 5));
			}
		}
	};
	public static FightAction Heal = new FightAction("Heal", "Holy touch for your friend",
			FightAction.PossibleTargetEnum.range) {
		@Override
		public void action(Fighter actor, Fighter targets) {
			if (targets != null) {
				targets.attacked(new AttackSourse(actor,targets, AttackType.holy, actor.wisdom * -3));
			}
		}
	};
	public static FightAction MeteorRain = new FightAction("Meteor Rain", "Oh my g~~ , this is Armageddon!",
			FightAction.PossibleTargetEnum.massively) {
		@Override
		public void action(Fighter actor, Fighter targets) {
			if (targets != null) {
				targets.attacked(new AttackSourse(actor,targets, AttackType.fire, actor.wisdom * 2));
				targets.attacked(new AttackSourse(actor,targets, AttackType.breaking, actor.wisdom * 2));
			}
		}
	};
	public static FightAction Push = new FightAction("Push", "Push-Ro-Dah!", FightAction.PossibleTargetEnum.male) {
		@Override
		public void action(Fighter actor, Fighter targets) {
			if (targets != null) {
				targets.attacked(new AttackSourse(actor,targets, AttackType.breaking, actor.vim * 5));
			}
		}
	};
}
