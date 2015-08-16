package ru.asemty.catvenure.main.engine;

import java.util.ArrayList;

import ru.asemty.catvenure.main.engine.fight.actions.FightActionFireBall;
import ru.asemty.catvenure.main.engine.fight.actions.FightActionHeal;
import ru.asemty.catvenure.main.engine.fight.actions.FightActionMeteorRain;

public class Party {
	public int gold = 100;
	public ArrayList<Item> bag = new ArrayList<Item>();
	public Cat[] cats;
	public Party(){
		this.cats = new Cat[] { 
				Cat.getRandomCat().addItem(Items.hat), 
				Cat.getRandomCat().addItem(Items.shield), 
				null,
				null, 
				null, 
				null };
		cats[0].actions.add(new FightActionFireBall());
		cats[0].actions.add(new FightActionMeteorRain());
		cats[1].actions.add(new FightActionHeal());
	}

	public Party addItem(Item item) {
		this.bag.add(item);
		
		return this;
	}
}
