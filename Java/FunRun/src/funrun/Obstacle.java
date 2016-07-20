package funrun;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

// extendable class to keep track of each of the different types of obstacles
public class Obstacle implements FunRunConstants {
	
	// variables common to all obstacles
	int x, y;
	int width, height;
	int speed;
	int damage;
	boolean collide;					// determines whether or not the obstacle has hit the player; can only hit once per run across the screen
	String name;						// obstacle type, used to determine which sprites to use
	int spriteCounter;					// increments to determine which sprite to display
	ArrayList<BufferedImage> sprites;	// holds all of the sprites for a given obstacle
	int numSprites;						// number of different sprites, used to add the correct number of images to the ArrayList
	
	// parent constructor: first step of initialization
	public Obstacle() {
		sprites = new ArrayList<BufferedImage>();
		spriteCounter = 0;
		// initial x is off the right side of the screen, anywhere in an area the size of the JFrame.
		x = APPLICATION_WIDTH + (int) Math.ceil(Math.random() * APPLICATION_WIDTH);
		collide = false;
	}
	
	// the second step of initialization is the child constructor. See Stump.java, Croco.java, Spore.java, Carrot.java
	
	// init() is the last step of the initialization. It is called from the child constructor after
	// the variables that are specific to the particular type of object have been initialized
	public void init() {
		// initial y is anywhere within the bounds of the JFrame
		y = (int) Math.floor(Math.random() * (APPLICATION_HEIGHT - height - 22)) + 22;
		// add sprite images to the ArrayList, using numSprites to get the correct numbers
		for(int i = 0; i < numSprites; i++) {
			try {
				BufferedImage newSprite = ImageIO.read(new File(name + i + ".png"));
				sprites.add(newSprite);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// used to move the obstacle towards the left side of the screen
	public void move() {
		x -= speed;
		spriteCounter++;
	}
	
}
