package ru.asemty.catvenure.main.engine.fight;

public class AttackSourse {
	public Fighter sourse;
	public String type;
	public int damage;
	public AttackSourse(Fighter f,String type,int damage){
		this.sourse=f;
		this.type=type;
		this.damage=damage;
	}
}
