package funrun;

public class Stump extends Obstacle {
	
	// initialize the values to be specific to a stump
	public Stump() {
		name = "stump";
		width = 60;
		height = 50;
		speed = 4;
		damage = 10;
		numSprites = 4;
		super.init();
	}
	
}
