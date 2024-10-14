/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.leopong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 *
 * @author L_Rag
 */
public class GamePanel extends javax.swing.JPanel implements Runnable{
    static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle[] paddle;
	Ball ball;
	Score p1Score, p2Score;
	
	GamePanel(){
		newPaddles();
		newBall(Color.white);
		p1Score = new Score(Color.blue, (int) (GAME_WIDTH*0.43),50,GAME_WIDTH,GAME_HEIGHT);
                p2Score = new Score(Color.green, (int) (GAME_WIDTH*0.51),50,GAME_WIDTH,GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void newBall(Color color) {
            random = new Random();
            ball = new Ball(color,(GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
	}
	public void newPaddles() {
            paddle = new Paddle[2];
            paddle[0] = new Paddle(Color.blue,0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
            paddle[1] = new Paddle(Color.green,GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
	}
    @Override
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	public void draw(Graphics g) {
		paddle[0].draw(g);
		paddle[1].draw(g);
		ball.draw(g);
		p1Score.draw(g);
                p2Score.draw(g);
Toolkit.getDefaultToolkit().sync();

	}
	public void move() {
		paddle[0].move();
		paddle[1].move();
		ball.move();
	}
	public void checkCollision() {
		
		//bounce ball off top & bottom window edges
		if(ball.y <=0) {
			ball.setYDirection(-ball.yVelocity);
		}
		if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
			ball.setYDirection(-ball.yVelocity);
		}
		//bounce ball off paddles
		if(ball.intersects(paddle[0])) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++; //optional for more difficulty
			if(ball.yVelocity>0)
				ball.yVelocity++; //optional for more difficulty
			else
				ball.yVelocity--;
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		if(ball.intersects(paddle[1])) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++; //optional for more difficulty
			if(ball.yVelocity>0)
				ball.yVelocity++; //optional for more difficulty
			else
				ball.yVelocity--;
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		//stops paddles at window edges
		if(paddle[0].y<=0)
			paddle[0].y=0;
		if(paddle[0].y >= (GAME_HEIGHT-PADDLE_HEIGHT))
			paddle[0].y = GAME_HEIGHT-PADDLE_HEIGHT;
		if(paddle[1].y<=0)
			paddle[1].y=0;
		if(paddle[1].y >= (GAME_HEIGHT-PADDLE_HEIGHT))
			paddle[1].y = GAME_HEIGHT-PADDLE_HEIGHT;
		//give a player 1 point and creates new paddles & ball
		if(ball.x <=0) {
			p2Score.player++;
			newBall(paddle[1].getColor());
		}
		if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
			p1Score.player++;
			newBall(paddle[0].getColor());
		}
	}
    @Override
	public void run() {
		//game loop
		long lastTime = System.nanoTime();
		float amountOfTicks =60f;
		float ns = 1000000000 / amountOfTicks;
		float delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
	public class AL extends KeyAdapter{
                @Override
		public void keyPressed(KeyEvent e) {
			paddle[0].keyPressed(e);
			paddle[1].keyPressed(e);
		}
                @Override
		public void keyReleased(KeyEvent e) {
			paddle[0].keyReleased(e);
			paddle[1].keyReleased(e);
		}
	}
}
