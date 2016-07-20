package funrun;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

// This class creates a Player object
public class Player implements FunRunConstants {
	
	// variables for the player object
	int x, y;
	int width, height;
	int health;
	int score;
	int hurt;			// used to blink the sprite on and off when hurt
	int spriteCount;	// increments to determine which sprite to display
	ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
	
	// used to determine which direction to move the player
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int UP = 3;
	public static final int DOWN = 4;
	
	// constructor for the player class - initialize values
	public Player() {
		// load images into ArrayList
		for(int i = 0; i < 5; i++) {
			try {
				BufferedImage newSprite = ImageIO.read(new File("bunny" + i + ".png"));
				sprites.add(newSprite);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		width = PLAYER_WIDTH;
		height = PLAYER_HEIGHT;
		x = 80;
		y = APPLICATION_HEIGHT/2 - PLAYER_HEIGHT/2;
		health = 100;
		score = 0;
		hurt = 0;
		spriteCount = 0;
	}
	
	// move the player depending on which keys are pressed down in the controller
	public void move(int direction) {
		if(direction == LEFT) {
			x -= PLAYER_SPEED;
			if(x < 0) {
				x = 0;
			}
		}
		if(direction == RIGHT) {
			x += PLAYER_SPEED;
			if(x > APPLICATION_WIDTH/2 - width) {
				x = APPLICATION_WIDTH/2 - width;
			}
		}
		if(direction == UP) {
			y -= PLAYER_SPEED;
			if(y < ZERO) {
				y = ZERO;
			}
		}
		if(direction == DOWN) {
			y += PLAYER_SPEED;
			if(y > APPLICATION_HEIGHT - height) {
				y = APPLICATION_HEIGHT - height;
			}
		}
	}
	
}
