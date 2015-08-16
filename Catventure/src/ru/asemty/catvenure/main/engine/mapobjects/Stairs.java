package ru.asemty.catvenure.main.engine.mapobjects;

import ru.asemty.catvenure.main.engine.Engine;

public class Stairs implements IMapObject{

	@Override
	public void interact() {
		Engine.goToVillage();
	}

	@Override
	public String getSprite() {
		// TODO Auto-generated method stub
		return "stairs";
	}

}
