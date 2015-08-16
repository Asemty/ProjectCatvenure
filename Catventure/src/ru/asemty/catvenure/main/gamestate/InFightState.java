package ru.asemty.catvenure.main.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import lib.Sprites;
import ru.asemty.catvenure.main.GameConst;
import ru.asemty.catvenure.main.engine.Cat;
import ru.asemty.catvenure.main.engine.Dungeon;
import ru.asemty.catvenure.main.engine.Engine;
import ru.asemty.catvenure.main.engine.EquipableItem;
import ru.asemty.catvenure.main.engine.Item;
import ru.asemty.catvenure.main.engine.enemy.Enemy;
import ru.asemty.catvenure.main.engine.fight.Fighter;
import ru.asemty.catvenure.main.engine.fight.actions.FightAction;
import ru.asemty.catvenure.main.engine.mapobjects.EnemyParty;

public class InFightState implements IGameState {

	EnemyParty enemyParty;
	Point enemyPartyLocation;
	ArrayList<Fighter> fighterSortedList;
	int currentFighter;
	int currentAction;
	int currentTarget;
	static Polygon arrow = new Polygon(new int[] { 15, 15, 0 }, new int[] { 0, 15, 15 }, 3);
	static Color red = new Color(255, 64, 64, 200);
	static Color green = new Color(64, 255, 64, 200);
	static Comparator<Fighter> fighterSpeedCompare = new Comparator<Fighter>() {

		@Override
		public int compare(Fighter o1, Fighter o2) {
			return o2.getSpeed() - o1.getSpeed();
		}

	};

	public InFightState(EnemyParty enemyParty, Point enemyLoc) {
		this.enemyParty = enemyParty;
		this.enemyPartyLocation = enemyLoc;
		this.nextTurn();
		this.turn();
	}

	@Override
	public void Draw(Graphics gr) {
		gr.drawImage(Sprites.getImage("fightBackGround"), 0, 0, null);
		gr.setColor(new Color(255, 255, 255, 200));
		gr.fillRect(0, 0, GameConst.screenWidth, GameConst.screenHeight / 3);
		gr.setColor(Color.black);
		ArrayList<FightAction> fa = this.getCurrentFighter().actions;
		gr.drawString(fa.get(this.currentAction).getDescription(), 0, 12);
		for (int i = 0; i < this.getCurrentFighter().actions.size(); i++) {
			gr.setColor(i == this.currentAction ? Color.black : Color.gray);
			gr.drawString(fa.get(i).getName(), 600, 12 + 14 * i);
		}
		int x;
		int y;
		FightAction.PossibleTargetEnum pte = this.getPossibleTarget();
		for (int i = 0; i < 6; i++) {
			x = 60 + 170 * (i / 3) - 50 * i / 3;
			y = 260 + 100 * (i % 3);

			if (enemyParty.enemis[i] != null) {

				gr.drawImage(Sprites.getImage(enemyParty.enemis[i].sprite), x, y, null);
				
				if (this.getCurrentFighter() == enemyParty.enemis[i]) {
					gr.setColor(Color.magenta);
					gr.fillRect(x, y, 20, 20);
				}
				gr.setColor(Color.black);
				gr.drawString(enemyParty.enemis[i].hp+"", x, y+12);

			}
			gr.setColor(!this.isPossible(i) ? red : green);
			if (pte == FightAction.PossibleTargetEnum.massively
					|| (i == this.currentTarget && (pte == FightAction.PossibleTargetEnum.range || pte == FightAction.PossibleTargetEnum.male))) {

				gr.fillRect(x + 20, y, 20, 20);
			}

			x = 610 + 170 * (i / 3) - 50 * i / 3;
			y = 260 + 100 * (i % 3);
			if (Engine.party.cats[i] != null) {

				gr.drawImage(Sprites.getImage("cat"), x, y, null);
				for (Item item : Engine.party.cats[i].bag) {
					if (item instanceof EquipableItem) {
						gr.drawImage(Sprites.getImage(((EquipableItem) item).sprite), x, y, null);
					}
				}
				
				if (this.getCurrentFighter() == Engine.party.cats[i]) {
					gr.setColor(Color.magenta);
					gr.fillRect(x, y, 20, 20);
				}
				gr.setColor(Color.black);
				gr.drawString(Engine.party.cats[i].hp+"", x, y+12);
			}
			gr.setColor(!this.isPossible(i) ? red : green);
			if (pte == FightAction.PossibleTargetEnum.friendlyMassively
					|| (i == this.currentTarget && (pte == FightAction.PossibleTargetEnum.friendly || pte == FightAction.PossibleTargetEnum.self))) {

				gr.fillRect(x + 20, y, 20, 20);
			}
		}
	}

	@Override
	public void KeyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			Engine.goToVillage();
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			currentAction--;
			if (currentAction == -1) {
				currentAction = this.getCurrentFighter().actions.size() - 1;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			currentAction++;
			if (currentAction == this.getCurrentFighter().actions.size()) {
				currentAction = 0;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			currentTarget--;
			if (currentTarget == -1) {
				currentTarget = 5;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			currentTarget++;
			if (currentTarget == 6) {
				currentTarget = 0;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			FightAction.PossibleTargetEnum pte = this.getPossibleTarget();
			if(pte == FightAction.PossibleTargetEnum.massively || pte == FightAction.PossibleTargetEnum.friendlyMassively){
				for(int i=0;i<6;i++){
					if(this.isPossible(i)){
						this.getCurrentFighter().actions.get(this.currentAction).action(this.getCurrentFighter(), pte == FightAction.PossibleTargetEnum.massively?enemyParty.enemis[i]:Engine.party.cats[i]);
					}
				}
				turn();
			}else if(this.isPossible(currentTarget)){
				this.getCurrentFighter().actions.get(this.currentAction).action(this.getCurrentFighter(), pte == FightAction.PossibleTargetEnum.range || pte == FightAction.PossibleTargetEnum.male?enemyParty.enemis[currentTarget]:Engine.party.cats[currentTarget]);
				turn();
			}
		}
	}

	public boolean isPossible(int i) {
		FightAction.PossibleTargetEnum pte = this.getPossibleTarget();
		if (pte == FightAction.PossibleTargetEnum.massively
				|| (i == this.currentTarget && (pte == FightAction.PossibleTargetEnum.range || pte == FightAction.PossibleTargetEnum.male))) {

			return !(enemyParty.enemis[i] == null || enemyParty.enemis[i].isDead() || (pte == FightAction.PossibleTargetEnum.male && i <= 2));
		}
		if (pte == FightAction.PossibleTargetEnum.friendlyMassively
				|| (i == this.currentTarget && (pte == FightAction.PossibleTargetEnum.friendly || pte == FightAction.PossibleTargetEnum.self))) {
			return !(Engine.party.cats[i] == null || Engine.party.cats[i].isDead() || (pte == FightAction.PossibleTargetEnum.self && this
					.getCurrentFighter() != Engine.party.cats[i]));
		}

		return false;

	}

	@Override
	public void Step() {
		// TODO Auto-generated method stub

	}

	public void nextTurn() {
		fighterSortedList = getFigterSortedList();
		currentFighter = 0;

	}

	public void turn() {
		currentFighter++;
		currentAction=0;
		if (!this.checkCatIsLive()) {
			this.fightEnd(false);
			return;
		}
		if (!this.checkEnemyIsLive()) {
			this.fightEnd(true);
			return;
		}
		if (currentFighter == fighterSortedList.size()) {
			this.nextTurn();
		}
		Fighter fighter = getCurrentFighter();
		if (fighter != null && !fighter.isDead()) {
			if (fighter instanceof Cat) {

			} else if (fighter instanceof Enemy) {
				turn();
			}
		}

	}

	public Fighter getCurrentFighter() {
		return (fighterSortedList != null && currentFighter < fighterSortedList.size()) ? fighterSortedList
				.get(currentFighter) : null;
	}

	public FightAction.PossibleTargetEnum getPossibleTarget() {
		return getCurrentFighter().actions.get(this.currentAction).getPossibleTarget();
	}

	public ArrayList<Fighter> getFigterSortedList() {
		ArrayList<Fighter> result = new ArrayList<Fighter>();
		for (Cat cat : Engine.party.cats) {
			if (cat != null && !cat.isDead()) {
				result.add(cat);
			}
		}
		for (Enemy enemy : enemyParty.enemis) {
			if (enemy != null && !enemy.isDead()) {
				result.add(enemy);
			}
		}
		Collections.sort(result, fighterSpeedCompare);
		return result;
	}

	public void fightEnd(boolean win) {
		if(win){
			Engine.dungeon.getCurrentStage().objects[enemyPartyLocation.x][enemyPartyLocation.y]=null;
			Engine.goToDungeon();
		}else{
			Engine.dungeon= new Dungeon();
			Engine.goToVillage();
		}
	}

	public boolean checkCatIsLive() {
		for (Cat cat : Engine.party.cats) {
			if (cat!=null && !cat.isDead()) {
				return true;
			}
		}
		return false;
	}

	public boolean checkEnemyIsLive() {
		for (Enemy enemy : enemyParty.enemis) {
			if (enemy!=null && !enemy.isDead()) {
				return true;
			}
		}
		return false;
	}

}
