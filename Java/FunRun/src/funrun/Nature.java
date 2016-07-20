package funrun;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

// This class creates a Nature object
public class Nature implements FunRunConstants {

	// variables common to all nature objects
	int x, y;
	int width, height;
	BufferedImage sprite;
	int numSprites;

	// constructor for the Nature class
	public Nature() {
		width = 30;
		height = 30;
		x = (int) Math.ceil(Math.random() * APPLICATION_WIDTH);
		y = (int) Math.floor(Math.random() * (APPLICATION_HEIGHT - height - 22)) + 22;
		numSprites = 6;

		// pick a random plant image
		int type = (int) Math.floor(Math.random() * 6);
		try {
			sprite = ImageIO.read(new File("nature" + type + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// move the Nature objects
	public void move() {
		x -= 2;
		// if the object goes offscreen, it resets somewhere off the right side
		if (x < -width) {
			x = APPLICATION_WIDTH + (int) Math.ceil(Math.random() * APPLICATION_WIDTH);
			y = (int) Math.floor(Math.random() * (APPLICATION_HEIGHT - height - ZERO)) + ZERO;
		}
	}

}
