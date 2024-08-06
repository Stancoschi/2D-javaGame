package main;
import java.awt.Color;
import java.awt.Dimension;
import java.lang.classfile.instruction.NewMultiArrayInstruction;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	

	
	//Screen Settings
	final int originalTileSize = 16;  //16x16 Tile
	final int scale = 3;

	final int tileSize = originalTileSize * scale; //48x48 Tile
	final int maxScreenColumn = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize*maxScreenColumn; //768 pixels
	final int screenHeigth = tileSize*maxScreenRow; //576 pixels
	
	Thread gameThread;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeigth));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //Better Rendering performance
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	@Override
	public void run() {
		
		while(gameThread != null) {
			System.out.println("The game is running");
		}
	}
	
	

}
