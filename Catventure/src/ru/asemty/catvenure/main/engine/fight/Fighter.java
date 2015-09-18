package ru.asemty.catvenure.main.engine.fight;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import lib.Sprites;
import ru.asemty.catvenure.main.engine.Cat;
import ru.asemty.catvenure.main.engine.EquipableItem;
import ru.asemty.catvenure.main.engine.Item;
import ru.asemty.catvenure.main.engine.enemy.Enemy;
import ru.asemty.catvenure.main.engine.fight.actions.FightAction;
import ru.asemty.catvenure.main.engine.rolesystem.passiveskill.PassiveSkill;

public class Fighter {
	// хитрость, мудрость, сила
	public int hp, mp, mhp, mmp, cunning, wisdom, vim;
	public String name;
	public Image img;
	public ArrayList<FightAction> actions = new ArrayList<FightAction>();
	public ArrayList<Buff> buffs = new ArrayList<Buff>();
	public boolean isControle;

	public Fighter(Cat cat) {
		if (buffs != null) {
			if (cat.passiveSkills != null)
				for (PassiveSkill skills : cat.passiveSkills) {
					buffs.addAll(skills.getBuffs());
				}
			if (cat.items != null)
				for (Item item : cat.items) {
					buffs.addAll(item.getBuffs());
				}
		}
		this.mhp = cat.vim * 10;
		this.mmp = cat.wisdom * 10;
		this.hp = this.mhp;
		this.mp = this.mmp;

		this.cunning = cat.cunning;
		this.wisdom = cat.wisdom;
		this.vim = cat.vim;
		this.actions = cat.actions;
		this.name = cat.name;
		img = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.drawImage(Sprites.getImage("cat"), 32, 0, null);
		for (Item item : cat.bag) {
			if (item instanceof EquipableItem) {
				g.drawImage(Sprites.getImage(((EquipableItem) item).sprite), 32, 0, null);
			}
		}
		this.isControle = true;
	}

	public Fighter(Enemy enemy) {
		this.hp = enemy.hp;
		this.mp = enemy.mp;

		this.cunning = enemy.cunning;
		this.wisdom = enemy.wisdom;
		this.vim = enemy.vim;
		this.actions = enemy.actions;
		this.name = enemy.name;
		this.img = Sprites.getImage(enemy.sprite);
		this.isControle = false;
	}

	public boolean isDead() {
		return hp <= 0;
	}

	public int getSpeed() {
		int speed = cunning * 3 + wisdom - vim / 2;
		for (Buff buff : buffs) {
			speed = buff.speedModifire(speed);
		}
		return speed;
	}

	public void attacked(AttackSourse as) {
		for (Buff buff : as.sourse.buffs) {
			as = buff.attacks(as);
		}

		for (Buff buff : buffs) {
			as = buff.attacked(as);
		}
		this.hp -= as.damage;
	}

}
