package ru.asemty.catvenure.main.engine.rolesystem.perquisite;


public abstract class Perquisite {
	public int level;
	public String description;
	public Perquisite(){
		this.level=0;
		PerqLevelDataInit();
	}
	public abstract void PerqLevelDataInit();
	public abstract String getName();

}
