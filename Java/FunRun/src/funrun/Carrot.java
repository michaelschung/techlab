package funrun;

public class Carrot extends Obstacle {
	
	// initialize the values to be specific to a carrot
	public Carrot() {
		name = "carrot";
		width = 20;
		height = 40;
		speed = 4;
		damage = -5;		// negative damage = healing
		numSprites = 4;
		super.init();
	}
	
	// the carrot object has a separate special method to make it run away
	public void carrotMove(int playerX, int playerY, int playerHeight) {
		// if the player is in the upper half of the carrot's y-range, the carrot will move downwards
		if(playerY + playerHeight > y && playerY <= y) {
			// if the player is approaching the carrot from the front, the carrot will walk backwards
			if(playerX < x) {
				speed = -1;
			}
			// if the player is approaching the carrot from behind, the carrot will run faster
			else {
				speed = 5;
			}
			y += 2;	// move down
		}
		// if the player is in the lower half of the carrot's y-range, the carrot will move upwards
		else if(playerY > y && playerY <= y + height) {
			// if the player is approaching the carrot from the front, the carrot will walk backwards
			if(playerX < x) {
				speed = -1;
			}
			// if the player is approaching the carrot from behind, the carrot will run faster
			else {
				speed = 5;
			}
			y -= 2;	// move up
		} else {
			speed = 4;		// otherwise, just walk normally
		}
		
		// make sure it's not going off the screen
		if(y < ZERO) {
			y = ZERO;
		} else if(y > APPLICATION_HEIGHT - height) {
			y = APPLICATION_HEIGHT - height;
		}
	}
	
}
