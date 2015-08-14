package ru.asemty.catvenure.main.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import lib.Sprites;
import ru.asemty.catvenure.main.GameConst;
import ru.asemty.catvenure.main.engine.Engine;
import ru.asemty.catvenure.main.engine.Stage;
import ru.asemty.catvenure.main.engine.mapobjects.EnemyParty;
import ru.asemty.catvenure.main.engine.mapobjects.IMapObject;

public class InDungeonState implements IGameState {

	@Override
	public void Draw(Graphics gr) {
		gr.setColor(Color.black);
		gr.fillRect(0, 0, GameConst.screenWidth, GameConst.screenHeight);
		IMapObject obj;
		int x, y;
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 20; j++) {
				gr.setColor(Engine.dungeon.getCurrentStage().passability[i][j] ? Color.gray : Color.black);
				x = i * 24 + 100;
				y = j * 24 + 60;
				gr.fillRect(x, y, 24, 24);
				obj = Engine.dungeon.getCurrentStage().objects[i][j];
				if (obj != null) {
					if (obj instanceof EnemyParty) {
						gr.drawImage(Sprites.getImage(((EnemyParty) obj).getSprite()), x, y, null);
					}
				}
			}
		}
		gr.drawImage(Sprites.getImage("catIcon"), Engine.dungeon.getCurrentStage().catPatyPos.x * 24 + 100,
				Engine.dungeon.getCurrentStage().catPatyPos.y * 24 + 60, null);
	}

	@Override
	public void KeyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			Engine.goToVillage();
		}
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
