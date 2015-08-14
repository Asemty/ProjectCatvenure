package ru.asemty.catvenure.main.engine;

import java.util.ArrayList;

public class Dungeon {
	public ArrayList<Stage> stages;
	public int currentStage;
	public Dungeon(){
		stages = new ArrayList<Stage>();
		currentStage=0;
		stages.add(Stage.generate());
	}
	public Stage getCurrentStage(){
		return stages.get(currentStage);
	}
}
