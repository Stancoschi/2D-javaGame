package object;

import javax.imageio.ImageIO;

public class obj_Chest extends SuperObject{
	
	public obj_Chest() {
		name = "Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
