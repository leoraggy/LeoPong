/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.leopong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 *
 * @author L_Rag
 */
public class Paddle extends Rectangle{
        int id;
	int yVelocity;
	int speed = 10;
        Color color;
	
	Paddle(Color color,int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
                this.color = color;
		this.id=id;
	}
	
	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1 -> {
                    if(e.getKeyCode()==KeyEvent.VK_W) {
                        setYDirection(-speed);
                    }
                    if(e.getKeyCode()==KeyEvent.VK_S) {
                        setYDirection(speed);
                    }
            }
		case 2 -> {
                    if(e.getKeyCode()==KeyEvent.VK_UP) {
                        setYDirection(-speed);
                    }
                    if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                        setYDirection(speed);
                    }
            }
		}
	}
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1 -> {
                    if(e.getKeyCode()==KeyEvent.VK_W) {
                        setYDirection(0);
                    }
                    if(e.getKeyCode()==KeyEvent.VK_S) {
                        setYDirection(0);
                    }
            }
		case 2 -> {
                    if(e.getKeyCode()==KeyEvent.VK_UP) {
                        setYDirection(0);
                    }
                    if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                        setYDirection(0);
                    }
            }
		}
	}
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}
	public void move() {
		y= y + yVelocity;
	}
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
        public Color getColor(){
            return color;
        }
}
    