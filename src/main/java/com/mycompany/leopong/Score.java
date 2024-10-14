/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.leopong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author L_Rag
 */
public class Score extends Rectangle{
    	static int GAME_WIDTH;
	static int GAME_HEIGHT;
        Color color;
	int player, x,y;
	
	Score(Color color,int x,int y,int GAME_WIDTH, int GAME_HEIGHT){
            this.color = color;
            this.x = x;
            this.y = y;
            Score.GAME_WIDTH = GAME_WIDTH;
            Score.GAME_HEIGHT = GAME_HEIGHT;
	}
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Consolas",Font.PLAIN,60));
		
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		
                g.setColor(color);
		g.drawString(String.valueOf(player/10)+String.valueOf(player%10), x,y);
	}
}
