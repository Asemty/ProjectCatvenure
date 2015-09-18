package ru.asemty.catvenure.main.engine.fight;

import ru.asemty.catvenure.main.engine.rolesystem.AttackType;

public class AttackSourse {
	public Fighter sourse;
	public Fighter target;
	public AttackType type;
	public int damage;
	public AttackSourse(Fighter attacker,Fighter defender,AttackType type,int damage){
		this.sourse=attacker;
		this.type=type;
		this.damage=damage;
		this.target=defender;
	}
}
