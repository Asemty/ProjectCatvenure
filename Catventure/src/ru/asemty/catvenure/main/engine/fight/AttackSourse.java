package ru.asemty.catvenure.main.engine.fight;

public class AttackSourse {
	public Fighter sourse;
	public boolean magic;
	public int damage;
	public AttackSourse(Fighter f,boolean magic,int damage){
		this.sourse=f;
		this.magic=magic;
		this.damage=damage;
	}
}
