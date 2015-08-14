package ru.asemty.catvenure.main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

import ru.asemty.catvenure.main.gamestate.IGameState;
import ru.asemty.catvenure.main.gamestate.InMenuState;
import lib.MainClass;

public class Game extends MainClass{
	private static final long serialVersionUID = -8736621867946390610L;
	public static IGameState curentState;
	public static Random rand=new Random();
	public static void main(String[] args) {
		Game.mainFunc(args, GameConst.screenWidth, GameConst.screenHeight, 20, new Game());
		Game.frame.setTitle("Project \"Catventure\"");
	}
	public Game(){
		SpriteController.init();
		curentState=new InMenuState();
	}
	@Override
	public void Draw(Graphics gr) {
		if(curentState!=null)curentState.Draw(gr);
		super.Draw(gr);
	}
	@Override
	public void KeyPressed(KeyEvent e) {
		if(curentState!=null)curentState.KeyPressed(e);
		super.KeyPressed(e);
	}
	@Override
	public void Step() {
		if(curentState!=null)curentState.Step();
		super.Step();
	}
}
