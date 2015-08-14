package ru.asemty.catvenure.main.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import lib.Sprites;
import ru.asemty.catvenure.main.GameConst;
import ru.asemty.catvenure.main.engine.Engine;
import ru.asemty.catvenure.main.engine.EquipableItem;
import ru.asemty.catvenure.main.engine.Item;

public class InCastleState implements IGameState {
	public static int currentCat = 0;
	public static int currentItem = 0;
	public static boolean inStorage = false;
	public static boolean editInventory = false;

	@Override
	public void Draw(Graphics gr) {
		gr.setColor(Color.lightGray);
		gr.drawRect(0, 0, GameConst.screenWidth, GameConst.screenHeight);
		gr.setColor(Color.darkGray);

		int x, y;
		for (int i = 0; i < 6; i++) {
			x = 10 + (i / 3) * 300;
			y = 10 + (i % 3) * 200;
			if (i == currentCat) {
				gr.drawRect(x - 5, y - 5, 74, 138);
				if (editInventory) {
					gr.drawRect(x - 4, y - 4, 72, 136);
				}
			}
			if (i < Engine.party.cats.length && Engine.party.cats[i] != null) {

				gr.drawImage(Sprites.getImage("cat"), x, y, null);
				gr.drawString(Engine.party.cats[i].name, x + 75, y);

				gr.drawString("Cunning: " + Engine.party.cats[i].cunning, x + 75, y + 16);
				gr.drawString("Wisdom: " + Engine.party.cats[i].wisdom, x + 75, y + 32);
				gr.drawString("Vim: " + Engine.party.cats[i].vim, x + 75, y + 48);

				for (int j = 0; j < Engine.party.cats[i].bag.size(); j++) {
					Item item = Engine.party.cats[i].bag.get(j);
					gr.drawString(item.name, x + 75, y + 68 + 12 * j);
					if (item instanceof EquipableItem) {
						gr.drawImage(Sprites.getImage(((EquipableItem) item).sprite), x, y, null);
					}
					if (i == currentCat && editInventory && !inStorage && j == currentItem) {
						gr.drawLine(x + 73, y + 56 + 12 * j, x + 73, y + 70 + 12 * j);
						gr.drawLine(x + 73, y + 70 + 12 * j, x + 113, y + 70 + 12 * j);
					}
				}
			}
		}
		int i = 0;
		for (Item item : Engine.party.bag) {
			gr.drawString(item.name, 620, 10 + i * 12);
			if (editInventory && inStorage && i == currentItem) {
				gr.drawLine(618, 0+ i * 12, 618, 12+ i * 12);
				gr.drawLine(618, 12+ i * 12, 658, 12+ i * 12);
			}
			i++;
		}
	}

	@Override
	public void KeyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

			if (editInventory) {
				editInventory = false;
			} else {
				Engine.goToVillage();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			if (editInventory) {
				if (inStorage) {
					inStorage = false;
					currentItem = Engine.party.cats[currentCat].bag.size() > 0 ? 0 : -1;
				}
			} else {
				if (currentCat > 2) {
					currentCat -= 3;
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			if (editInventory) {
				if (!inStorage) {
					inStorage = true;
					currentItem = Engine.party.bag.size() > 0 ? 0 : -1;
				}

			} else {
				if (currentCat < 3) {
					currentCat += 3;
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			if (editInventory) {
				if (currentItem > 0) {
					currentItem--;
				}
			} else {
				if (currentCat > 0) {
					currentCat--;
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			if (editInventory) {
				if (currentItem+1 < (inStorage ? Engine.party.bag.size() : Engine.party.cats[currentCat].bag.size())) {
					currentItem++;
				}
			} else {
				if (currentCat < 5) {
					currentCat++;
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (editInventory) {
				if (inStorage && currentItem>=0 && currentItem<Engine.party.bag.size()) {
					if(Engine.party.cats[currentCat].bag.size()<5){
						Engine.party.cats[currentCat].bag.add(Engine.party.bag.get(currentItem));
						Engine.party.bag.remove(currentItem);
						if(currentItem+1>Engine.party.bag.size()){
							currentItem--;
						}
					}
				}else if(!inStorage && currentItem>=0 && currentItem<Engine.party.cats[currentCat].bag.size()){
					Engine.party.bag.add(Engine.party.cats[currentCat].bag.get(currentItem));
					Engine.party.cats[currentCat].bag.remove(currentItem);
					if(currentItem+1>Engine.party.cats[currentCat].bag.size()){
						currentItem--;
					}
				}
			} else {
				if (currentCat < Engine.party.cats.length && Engine.party.cats[currentCat] != null) {
					editInventory = true;
					currentItem = Engine.party.cats[currentCat].bag.size() > 0 ? 0 : -1;
				}
			}

		}
	}

	@Override
	public void Step() {
		// TODO Auto-generated method stub

	}

}
