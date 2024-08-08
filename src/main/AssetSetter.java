package main;

import object.obj_Boots;
import object.obj_Chest;
import object.obj_Door;
import object.obj_key;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		
		this.gp = gp;
	}
	
	public void setObject() {
		
		gp.obj[0] = new obj_key();
		gp.obj[0].worldX = 23 *gp.tileSize;
		gp.obj[0].worldY = 7 * gp.tileSize;
		
		gp.obj[1] = new obj_key();
		gp.obj[1].worldX = 23 *gp.tileSize;
		gp.obj[1].worldY = 40 * gp.tileSize;
		
		gp.obj[2] = new obj_key();
		gp.obj[2].worldX = 37 *gp.tileSize;
		gp.obj[2].worldY = 7 * gp.tileSize;
		
		gp.obj[3] = new obj_Door();
		gp.obj[3].worldX = 10 *gp.tileSize;
		gp.obj[3].worldY = 11 * gp.tileSize;
		
		gp.obj[4] = new obj_Door();
		gp.obj[4].worldX = 8 *gp.tileSize;
		gp.obj[4].worldY = 28 * gp.tileSize;
		
		gp.obj[5] = new obj_Door();
		gp.obj[5].worldX = 12 *gp.tileSize;
		gp.obj[5].worldY = 22 * gp.tileSize;

		gp.obj[6] = new obj_Chest();
		gp.obj[6].worldX = 10 *gp.tileSize;
		gp.obj[6].worldY = 7 * gp.tileSize;
		
		gp.obj[7] = new obj_Boots();
		gp.obj[7].worldX = 37 *gp.tileSize;
		gp.obj[7].worldY = 42 * gp.tileSize;
	
	}
}
