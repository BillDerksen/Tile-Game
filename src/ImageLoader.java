import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1); //Als de image niet geladen wordt dan exit de game. Want zonder plaatjes kan je niet veel..
		}
		return null;
	}
	
	
}
