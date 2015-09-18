package ru.asemty.catvenure.main.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import lib.Sprites;
import ru.asemty.catvenure.main.engine.Dungeon;
import ru.asemty.catvenure.main.engine.Engine;
import ru.asemty.catvenure.main.engine.fight.Buff;
import ru.asemty.catvenure.main.engine.fight.Fighter;
import ru.asemty.catvenure.main.engine.fight.actions.FightAction;
import ru.asemty.catvenure.main.engine.mapobjects.EnemyParty;
import ru.asemty.catvenure.utilite.DrawHelper;

public class InFightState implements IGameState {

	Point enemyPartyLocation;
	ArrayList<Fighter> fighterSortedList;
	Fighter[] fighters = new Fighter[18];
	int currentAction;
	int currentFighter;
	int currentTarget;
	int currentStep;
	static Color red = new Color(255, 64, 64, 128);
	static Color green = new Color(64, 255, 64, 128);
	static Comparator<Fighter> fighterSpeedCompare = new Comparator<Fighter>() {

		@Override
		public int compare(Fighter o1, Fighter o2) {
			return o2.getSpeed() - o1.getSpeed();
		}

	};

	public InFightState(EnemyParty enemyParty, Point enemyLoc) {
		for (int i = 0; i < 9; i++) {
			if (enemyParty.enemis[i] != null)
				fighters[i] = new Fighter(enemyParty.enemis[i]);
			if (Engine.party.cats[i] != null)
				fighters[i + 9] = new Fighter(Engine.party.cats[i]);
		}
		this.enemyPartyLocation = enemyLoc;
		this.nextTurn();
		this.turn();
	}

	@Override
	public void Draw(Graphics gr) {
		gr.drawImage(Sprites.getImage("fightBackGround"), 0, 0, null);
		gr.drawImage(DrawHelper.FighterInfo(getCurrentFighter()), 0, 0, null);
		/*
		 * ArrayList<FightAction> fa = this.getCurrentFighter().actions; if
		 * (fa.size() > 0) {
		 * gr.drawString(fa.get(this.currentAction).description, 0, 12); for
		 * (int i = 0; i < this.getCurrentFighter().actions.size(); i++) {
		 * gr.setColor(i == this.currentAction ? Color.black : Color.gray);
		 * gr.drawString(fa.get(i).name, 600, 12 + 14 * i); } }
		 * FightAction.PossibleTargetEnum pte = this.getPossibleTarget();
		 */
		int x, y;
		for (int i = 0; i < 9; i++) {
			x = 60 + 170 * (i / 3) - 50 * i / 3;
			y = 260 + 100 * (i % 3);
			
			if(i==currentTarget || i+9==currentTarget){
				gr.drawImage(Sprites.getImage("arrow"), x+(i==currentTarget?420:0), y-128, null);
			}
			if(i==currentFighter || i+9==currentFighter){
				gr.setColor(Color.magenta);
				gr.drawOval(x+(i==currentFighter?420:0), y+100, 128, 28);
				gr.drawOval(x+(i==currentFighter?420:0)+1, y+101, 126, 26);
				gr.drawOval(x+(i==currentFighter?420:0)+2, y+102, 124, 24);
			}
			if (fighters[i] != null) {
				gr.drawImage(fighters[i].img, x, y, null);
				
			}
			/*
			 *if (pte ==
			 * FightAction.PossibleTargetEnum.massively || (i ==
			 * this.currentTarget && (pte ==
			 * FightAction.PossibleTargetEnum.range || pte ==
			 * FightAction.PossibleTargetEnum.male))) {
			 * gr.fillRect(x + 20, y, 20, 20); }
			 */
			if (fighters[i + 9] != null) {
				gr.drawImage(fighters[i+9].img, x + 420, y, null);
			}
			/*
			 if (pte ==
			 * FightAction.PossibleTargetEnum.massively || (i ==
			 * this.currentTarget && (pte ==
			 * FightAction.PossibleTargetEnum.massively || pte ==
			 * FightAction.PossibleTargetEnum.self))) {
			 * gr.fillRect(x + 20, y, 20, 20); }
			 */
		}
	}

	@Override
	public void KeyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			Engine.goToVillage();
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			currentTarget--;
			if (currentTarget%3 == 2 || currentTarget==-1) {
				currentTarget += 3;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			currentTarget++;
			if (currentTarget%3 ==0) {
				currentTarget -= 3;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			currentTarget-=3;
			if (currentTarget < 0) {
				currentTarget += 18;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			currentTarget+=3;
			if (currentTarget > 17) {
				currentTarget -= 18;
			}
		}

		/*if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			FightAction.PossibleTargetEnum pte = this.getPossibleTarget();
			if (pte == FightAction.PossibleTargetEnum.massively || pte == FightAction.PossibleTargetEnum.massively) {
				for (int i = 0; i < 18; i++) {
					if (this.isPossible(i)) {
						this.getCurrentFighter().actions.get(this.currentAction).action(this.getCurrentFighter(),
								pte == FightAction.PossibleTargetEnum.massively ? fighters[i] : fighters[i + 9]);
					}
				}
				turn();
			} else if (this.isPossible(currentTarget)) {
				this.getCurrentFighter().actions.get(this.currentAction)
						.action(this.getCurrentFighter(),
								pte == FightAction.PossibleTargetEnum.range
										|| pte == FightAction.PossibleTargetEnum.male ? fighters[currentTarget]
										: fighters[currentTarget]);
				turn();
			}
		}*/
	}

	public boolean isPossible(int i) {
		FightAction.PossibleTargetEnum pte = this.getPossibleTarget();
		if (pte == FightAction.PossibleTargetEnum.massively
				|| (i == this.currentTarget && (pte == FightAction.PossibleTargetEnum.range || pte == FightAction.PossibleTargetEnum.male))) {

			return !(fighters[i] == null || fighters[i].isDead() || (pte == FightAction.PossibleTargetEnum.male && i <= 2));
		}
		if (pte == FightAction.PossibleTargetEnum.massively
				|| (i == this.currentTarget && (pte == FightAction.PossibleTargetEnum.range || pte == FightAction.PossibleTargetEnum.self))) {
			return !(fighters[i + 9] == null || fighters[i + 9].isDead() || (pte == FightAction.PossibleTargetEnum.self && this
					.getCurrentFighter() != fighters[i + 9]));
		}

		return false;

	}

	@Override
	public void Step() {

	}

	public void nextTurn() {
		fighterSortedList = getFigterSortedList();
		currentFighter = 0;
	}

	public void turn() {
		ArrayList<Buff> del = new ArrayList<Buff>();
		for (Buff b : getCurrentFighter().buffs) {
			b.turn();
			if (b.time > 0) {
				b.time--;
			}
			if (b.time == 0) {
				del.add(b);
			}
		}
		getCurrentFighter().buffs.removeAll(del);
		currentFighter++;
		//currentAction = 0;
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
		if (fighter != null) {
			if (fighter.isDead()) {
				turn();
			}
			if (!fighter.isControle) {
				turn();
			}
		}

	}

	public Fighter getCurrentFighter() {
		return (fighterSortedList != null && currentFighter < fighterSortedList.size()) ? fighterSortedList
				.get(currentFighter) : null;
	}

	public FightAction.PossibleTargetEnum getPossibleTarget() {
		return /*getCurrentFighter().actions.size() > 0 ? getCurrentFighter().actions.get(this.currentAction).pte :*/ null;
	}

	public ArrayList<Fighter> getFigterSortedList() {
		ArrayList<Fighter> result = new ArrayList<Fighter>();
		for (int i = 0; i < 18; i++) {
			if (fighters[i] != null && !fighters[i].isDead() && fighters[i].getSpeed() > 0) {
				result.add(fighters[i]);
			}
		}
		Collections.sort(result, fighterSpeedCompare);
		return result;
	}

	public void fightEnd(boolean win) {
		if (win) {
			Engine.dungeon.getCurrentStage().objects[enemyPartyLocation.x][enemyPartyLocation.y] = null;
			Engine.goToDungeon();
		} else {
			Engine.dungeon = new Dungeon();
			Engine.goToVillage();
		}
	}

	public boolean checkCatIsLive() {
		for (int i = 9; i < 18; i++) {
			if (fighters[i] != null && !fighters[i].isDead()) {
				return true;
			}
		}
		return false;
	}

	public boolean checkEnemyIsLive() {
		for (int i = 0; i < 9; i++) {
			if (fighters[i] != null && !fighters[i].isDead()) {
				return true;
			}
		}
		return false;
	}

}
