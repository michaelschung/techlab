package funrun;

public class Spore extends Obstacle {
	
	// initialize the values to be specific to a spore
	public Spore() {
		name = "spore";
		width = 30;
		height = 30;
		speed = 12;
		damage = 2;
		numSprites = 6;
		super.init();
	}
	
}
