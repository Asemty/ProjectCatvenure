package ru.asemty.catvenure.main.engine;

public class EquipableItem extends Item {
	public String sprite;
	public EquipableItem(String name,String sprite) {
		super(name);
		this.sprite=sprite;
	}
}
