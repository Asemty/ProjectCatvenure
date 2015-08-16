package ru.asemty.catvenure.main.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

import lib.Sprites;
import ru.asemty.catvenure.main.GameConst;
import ru.asemty.catvenure.main.engine.Engine;
import ru.asemty.catvenure.main.engine.Stage;
import ru.asemty.catvenure.main.engine.mapobjects.IMapObject;

public class InDungeonState implements IGameState {

	public InDungeonState (){
	}
	@Override
	public void Draw(Graphics gr) {
		gr.setColor(Color.black);
		gr.fillRect(0, 0, GameConst.screenWidth, GameConst.screenHeight);
		IMapObject obj;
		int x, y;
		Point catPos=Engine.dungeon.getCurrentStage().catPatyPos;
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 20; j++) {
				gr.setColor(Engine.dungeon.getCurrentStage().passability[i][j] ? Color.gray : Color.black);
				
				x = (i-catPos.x+5) * 72+4;
				y = (j-catPos.y+3) * 72+48;
				gr.fillRect(x, y, 72, 72);
				obj = Engine.dungeon.getCurrentStage().objects[i][j];
				if (obj != null) {	
					gr.drawImage(Sprites.getImage(obj.getSprite()), x, y,x+72,y+72,0,0,24,24, null);
				}
			}
		}
		gr.drawImage(Sprites.getImage("catIcon"), 4+72*5, 48+72*3,4+72*6,48+72*4,0,0,24,24, null);
	}

	@Override
	public void KeyPressed(KeyEvent e) {
		int x = Engine.dungeon.getCurrentStage().catPatyPos.x;
		int y = Engine.dungeon.getCurrentStage().catPatyPos.y;
		Stage stage = Engine.dungeon.getCurrentStage();
		if (e.getKeyCode() == KeyEvent.VK_A) {
			if (x > 0 && stage.passability[x - 1][y]) {
				if (stage.objects[x - 1][y] == null) {
					stage.catPatyPos.x--;
				} else {
					stage.objects[x - 1][y].interact();
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			if (x < 24 && stage.passability[x + 1][y]) {
				if (stage.objects[x + 1][y] == null) {
					stage.catPatyPos.x++;
				} else {
					stage.objects[x + 1][y].interact();
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			if (y > 0 && stage.passability[x][y - 1]) {
				if (stage.objects[x][y - 1] == null) {
					stage.catPatyPos.y--;
				} else {
					stage.objects[x][y - 1].interact();
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			if (y < 19 && stage.passability[x][y + 1]) {
				if (stage.objects[x][y + 1] == null) {
					stage.catPatyPos.y++;
				} else {
					stage.objects[x][y + 1].interact();
				}
			}
		}
	}

	@Override
	public void Step() {
		// TODO Auto-generated method stub

	}

}
