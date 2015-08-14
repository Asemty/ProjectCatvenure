package ru.asemty.catvenure.main.engine;

import java.util.ArrayList;

import ru.asemty.catvenure.main.Game;

public class Cat {
	public String name;
	public int cunning, wisdom, vim, hp, mp,mhp,mmp,exp=0;
	public ArrayList<Item> bag = new ArrayList<Item>();
	public static String[] catNames=new String[]{
		"Tiger","Shadow","Oliver",
		"Jasper","Smokey","Jack",
		"Simba","Toby","Loki",
		"Rocky","George","Chester"
		};
	public static String[] catSecondNames=new String[]{
		"SMITH","JOHNSON","WILLIAMS",
		"JONES","BROWN","DAVIS",
		"MILLER","WILSON","MOORE",
		"TAYLOR","ANDERSON","THOMAS"
		};
	
	public static Cat getRandomCat(){
		return new Cat(catNames[Game.rand.nextInt(catNames.length)]+" "+catSecondNames[Game.rand.nextInt(catSecondNames.length)]);
	}
	public Cat addItem(Item item){
		this.bag.add(item);
		return this;
	}
	public Cat(String name,int abilPoint) {
		this.name=name;
		this.cunning=0;
		this.wisdom=0;
		this.vim=0;
		while(abilPoint>0){
			double chance=Game.rand.nextDouble();
			if(chance<1.0/3){
				cunning++;
			}else if(chance>=2.0/3){
				wisdom++;
			}else{
				vim++;
			}
			abilPoint--;
		}
	}
	public Cat(String name) {
		this(name,5);
	}
	
	public Cat(String name,int c,int w,int v) {
		this.name=name;
		this.cunning=c;
		this.wisdom=w;
		this.vim=v;
	}
}
