package ru.asemty.catvenure.main.engine;

import java.util.ArrayList;

public class Party {
	public int gold=100;
	public ArrayList<Item> bag=new ArrayList<Item>();
	public Cat[] cats= new Cat[]{Cat.getRandomCat().addItem(Items.hat),Cat.getRandomCat().addItem(Items.shield),null,null,null,null};
	public Party addItem(Item item){
		this.bag.add(item);
		return this;
	}
}
