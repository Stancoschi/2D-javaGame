package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable {
	

	
	//Screen Settings
	final int originalTileSize = 16;  //16x16 Tile
	final int scale = 3;

	public final int tileSize = originalTileSize * scale; //48x48 Tile
	final int maxScreenColumn = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize*maxScreenColumn; //768 pixels
	final int screenHeigth = tileSize*maxScreenRow; //576 pixels
	
	int FPS = 60;
	
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, keyH);
	
	//Set Player default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeigth));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //Better Rendering performance
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	@Override
//	public void run() {
//		
//		double drawInterval = 1000000000/FPS;  //Draw the screen every  (1 second/60) = 0.0166..s
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		long timer = 0;
//		int drawCount = 0;
//	
//		
//		while(gameThread != null) {
//			
//			long currentTime = System.nanoTime();
//			
// 			//Update the information for character positions
//			update();
//			
//			//Draw the screen with new information
//			repaint();
//			
//			
//			
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime/1000000;
//				
//				if(remainingTime < 0) {
//					remainingTime = 0;
//				}
//				
//				Thread.sleep((long)remainingTime);
//				
//				nextDrawTime += drawInterval;
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//	}
	
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >=1000000000) {
				System.out.println("FPS:" + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	public void update() {
		
		player.update();
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		player.draw(g2);
		
		g2.dispose();
		
	}
	

}
