import java.awt.image.BufferedImage;

// Aparte Assets class wordt gemaakt zodat er niet meerdere malen gecropt hoeft te worden uit een tile spreadsheet.

public class Assets {

	private static final int width = 64, height = 64;
	
	public static BufferedImage stone, sand, grass, mud;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/64sheet.png"));
		
		stone = sheet.crop(0, 0, width, height);
		sand = sheet.crop(0, height, width, height);
		grass = sheet.crop(0, height * 2, width, height);
		mud = sheet.crop(0, height * 3, width, height);
	}
	
}