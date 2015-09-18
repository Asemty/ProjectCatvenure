package ru.asemty.catvenure.main.engine;


import java.util.ArrayList;

import ru.asemty.catvenure.main.engine.fight.Buff;

public class Item {

	public String name;

	public Item(String name) {
		this.name = name;
	}

	public ArrayList<Buff> getBuffs(){
		return new ArrayList<Buff>();
	}
}
