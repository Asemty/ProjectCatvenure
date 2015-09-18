package ru.asemty.catvenure.utilite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import ru.asemty.catvenure.main.GameConst;
import ru.asemty.catvenure.main.engine.fight.Fighter;

public class DrawHelper {
	public static Image FighterInfo(Fighter f){
		int size = GameConst.screenHeight / 3;
		BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.setColor(GameConst.white);
		g.fillRect(0, 0, size, size);
		g.setColor(Color.black);
		g.drawRect(0, 0, size-1, size-1);
		g.drawString(f.name, 5, 15);
		g.drawString("Hp: "+f.hp+"/"+f.mhp, 5, 30+15);
		g.drawString("Mp: "+f.mp+"/"+f.mmp, 5, 45+15);
		g.drawString("Cunning: "+f.cunning, 5, 60+30);
		g.drawString("Wisdom: "+f.wisdom, 5, 75+30);
		g.drawString("Vim: "+f.vim, 5, 90+30);
		return img;
	}
}
