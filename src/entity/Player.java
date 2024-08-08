package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.CollisionChecker;
import main.GamePanel;
import main.KeyHandler;
import main.Sound;


/**
 * Represents the player character in the game.
 * Handles player movement, collision detection, and rendering.
 */

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	Sound sound;
	// Screen coordinates where the player is rendered
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	
	
	 /**
     * Constructs a Player object with the specified game panel and key handler.
     * 
     * @param gp the game panel
     * @param keyH the key handler
     */
	public Player (GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		// Calculate the screen coordinates for the player to be centered
		screenX = gp.screenWidth/2 - gp.tileSize/2;
		screenY= gp.screenHeigth/2 - gp.tileSize/2;
		
		// Initialize the solid area for collision detection
		solidArea = new Rectangle(8, 8, 8, 8 );
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		
		setDefaultValues();
		getPlayerImage();
	}
	
	
	
	/**
    * Loads the player images for different directions.
    */
	public void getPlayerImage() {
		try {
			System.out.println("Am ajuns aici");
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
     * Sets the default values for the player such as initial position, speed, and direction.
     */
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
		
	}
	
	
	/**
     * Updates the player's state based on key input and handles movement and collision detection.
     */
	public void update() {
		if(keyH.upPressed == true || keyH.downPressed == true ||
				keyH.leftPressed == true || keyH.rightPressed == true) {
			
			if(keyH.upPressed == true) {
				direction = "up";
			}
			else if(keyH.downPressed == true) {
				direction = "down";
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
			}
			
			
			// Check for tile collision
			collisonOn = false;
			gp.cChecker.checkTile(this);
			
			
			// Check for object collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			
			
			
			/// Move the player if no collision is detected
			if(collisonOn == false) {
				switch(direction) {
				case "up":    worldY -= speed;	break;
				case "down":  worldY += speed;  break;
				case "left":  worldX -= speed;  break;
				case "right": worldX += speed;  break;
				}
			}
			
			
			// Handle sprite animation
			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNum == 1  ) {
					spriteNum = 2;
				}
				else if(spriteNum == 2 ) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
			
		}
			
	}
	
	
	/**
     * Handles the logic for picking up objects.
     * 
     * @param i the index of the object in the game panel's object array
     */
	public void pickUpObject(int i) {
		
		if(i != 999) {
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Key":
				gp.play(1);
				hasKey++;
				gp.obj[i] = null;
				gp.ui.showMessage("You got a key");
				break;
			
			case "Door":
				if(hasKey > 0) {
					gp.play(3);
					gp.obj[i] = null;
					hasKey--;
					gp.ui.showMessage("The world is yours to explore");
				}
				else {
					gp.ui.showMessage("You need a key");
				}
				break;
			
			case "Boots":
				gp.ui.showMessage("You've got the boots of power");
				gp.play(2);
				speed += 2;
				gp.obj[i] = null;
				break;
				
			case "Chest":
				gp.ui.gameFinished =  true;
				gp.stopMusic();
				gp.play(4);
				break;
			}
		}
	}
	
	
	
	/**
     * Draws the player on the screen.
     * 
     * @param g2 the graphics context
     */
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch (direction) {
		case "up": 
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
			
		case "down": 
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
			
		case "left": 
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;	
			
		case "right": 
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
			
		}
		
		
		// Draw the player image at the calculated screen coordinates
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
		
	}	
	
}
