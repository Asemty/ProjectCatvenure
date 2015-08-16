package ru.asemty.catvenure.main.engine.fight.actions;

import ru.asemty.catvenure.main.engine.fight.Fighter;

public abstract class FightAction {
	public static enum PossibleTargetEnum {
		self, male, range, friendly, massively, friendlyMassively
	}
	public abstract void action(Fighter actor,Fighter targets);
	public abstract PossibleTargetEnum getPossibleTarget();
	public abstract String getName();
	public abstract String getDescription();
}
