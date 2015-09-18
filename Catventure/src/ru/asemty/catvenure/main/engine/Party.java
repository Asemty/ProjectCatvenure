package ru.asemty.catvenure.main.engine;

import java.util.ArrayList;

import ru.asemty.catvenure.main.engine.fight.actions.FightActionsList;

public class Party {
	public int gold = 100;
	public ArrayList<Item> bag = new ArrayList<Item>();
	public Cat[] cats;

	public Party() {
		this.cats = new Cat[9];
		this.cats[0] = Cat.getRandomCat().addItem(Items.hat);
		this.cats[1] = Cat.getRandomCat().addItem(Items.shield);
		this.cats[0].actions.add(FightActionsList.FireBall);
		this.cats[0].actions.add(FightActionsList.MeteorRain);
		this.cats[1].actions.add(FightActionsList.Heal);
	}

	public Party addItem(Item item) {
		this.bag.add(item);

		return this;
	}
}
