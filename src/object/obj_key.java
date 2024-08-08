package object;

import javax.imageio.ImageIO;

public class obj_key extends SuperObject {
	
	public obj_key() {
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
