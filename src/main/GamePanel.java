package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	

	
	//Screen Settings
	final int originalTileSize = 16;  //16x16 Tile
	final int scale = 3;

	public final int tileSize = originalTileSize * scale; //48x48 Tile
	public final int maxScreenColumn = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenColumn; //768 pixels
	public final int screenHeigth = tileSize * maxScreenRow; //576 pixels
	
	
	//World Settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;

	
	int FPS = 60;
	
	//System
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	public Sound se = new Sound();
	public Sound Music = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	Thread gameThread;
	
	//Entity and Objects
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[10];
	

	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth,screenHeigth));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //Better Rendering performance
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	
	public void setupGame() {
		
		aSetter.setObject();
		playMusic(0);
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
		
		//Tile
		tileM.draw(g2);
		
		//Object
		for(int i = 0; i< obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		//Player
		player.draw(g2);
		
		
		//UI
		ui.draw(g2);
		
		g2.dispose();
		
	}
	
	public void playMusic(int i) {
		
		
		Music.setFile(i);
		Music.play();
		Music.loop();
		
	}
	
	public void stopMusic() {
		
		Music.stop();
	}
	
	public void play(int i) {
		se.setFile(i);
		se.play();
	}
	

}
