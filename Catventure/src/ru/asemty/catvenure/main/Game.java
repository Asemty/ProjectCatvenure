package ru.asemty.catvenure.main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

import lib.MainClass;
import ru.asemty.catvenure.main.gamestate.IGameState;
import ru.asemty.catvenure.main.gamestate.InMenuState;

public class Game extends MainClass{
	private static final long serialVersionUID = -8736621867946390610L;
	public static IGameState curentState;
	public static Random rand=new Random();
	public static Game instance;
	public static int i;
	public static void main(String[] args) {
		Game.mainFunc(args, GameConst.screenWidth-12, GameConst.screenHeight-12, 50, new Game());
		Game.frame.setTitle("Project \"Catventure\"");
		Game.frame.setResizable(false);
	}
	public Game(){
		super();
		timer.stop();
		SpriteController.init();
		curentState=new InMenuState();
		Game.instance=this;
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
		this.repaint();
	}
	@Override
	public void Step() {
		if(curentState!=null)curentState.Step();
		super.Step();
		System.out.println("step"+i++);
		
	}
	
}
