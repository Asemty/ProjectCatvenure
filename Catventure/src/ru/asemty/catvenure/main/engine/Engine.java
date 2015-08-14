package ru.asemty.catvenure.main.engine;

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
	}

	public static void goToPotionShop() {
		Game.curentState= new InPotionShopState();
	}

	public static void goToCastle() {
		Game.curentState= new InCastleState();
	}

	public static void goToDungeon() {
		Game.curentState= new InDungeonState();
		for(int i=0;i<6;i++){
			Cat kitty= party.cats[i];
			if(kitty!=null){
				kitty.mhp=kitty.vim*10+10;
				kitty.mmp=kitty.wisdom*10;
				kitty.hp=kitty.mhp;
				kitty.mp=kitty.mmp;
			}
		}
	}

	public static void goToFight(EnemyParty enemyParty) {
		Game.curentState= new InFightState(enemyParty);
	}

}
