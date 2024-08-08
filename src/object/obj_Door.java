package object;

import javax.imageio.ImageIO;

public class obj_Door extends SuperObject{
	public obj_Door() {
		
		
		
		name = "Door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		collision = true;
	}
}
