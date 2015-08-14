package ru.asemty.catvenure.main;

import lib.Sprites;

public class SpriteController {

	public static void init() {
		Sprites.Init();
		Sprites.putToList("titleBackGround", "res/back/titleBack.png");
		Sprites.putToList("villageBackGround", "res/back/villageBack.png");
		Sprites.putToList("potionShopBackGround", "res/back/potionShopBack.png");
		Sprites.putToList("fightBackGround", "res/back/fightBack.png");
		
		Sprites.putToList("buildBack", "res/build/buildPlace.png");
		Sprites.putToList("potionShop", "res/build/potionShop.png");
		Sprites.putToList("dungeon", "res/build/dungeon.png");
		Sprites.putToList("castle", "res/build/castle.png");
		
		Sprites.putToList("cat", "res/cat/cat.png");
		
		Sprites.putToList("shield", "res/cat/shield.png");
		Sprites.putToList("hat", "res/cat/hat.png");
		
		Sprites.putToList("beholderIcon", "res/dungeon/beholderIcon.png");
		Sprites.putToList("beholder", "res/dungeon/beholder.png");
		Sprites.putToList("catIcon", "res/dungeon/catIcon.png");
	}
}
