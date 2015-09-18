package ru.asemty.catvenure.main.engine;

import java.awt.Point;

import ru.asemty.catvenure.main.Game;
import ru.asemty.catvenure.main.engine.mapobjects.EnemyParty;
import ru.asemty.catvenure.main.gamestate.InCastleState;
import ru.asemty.catvenure.main.gamestate.InDungeonState;
import ru.asemty.catvenure.main.gamestate.InFightState;
import ru.asemty.catvenure.main.gamestate.InPotionShopState;
import ru.asemty.catvenure.main.gamestate.InVillageState;

public class Engine {

	public static class Village{
		public static boolean potionShop=true;
	}
	public static Party party=new Party();
	public static Dungeon dungeon = new Dungeon();
	
	public static void goToVillage() {
		Game.curentState= new InVillageState();
		Game.timer.stop();
	}

	public static void goToPotionShop() {
		Game.curentState= new InPotionShopState();
		Game.timer.stop();
	}

	public static void goToCastle() {
		Game.curentState= new InCastleState();
		Game.timer.stop();
	}

	public static void goToDungeon() {
		Game.curentState= new InDungeonState();
		Game.timer.stop();
	}

	public static void goToFight(EnemyParty enemyParty,Point p) {
		Game.timer.start();
		Game.curentState= new InFightState(enemyParty,p);
	}

}
