package funrun;

public class Croco extends Obstacle {
	
	// initialize the values to be specific to a croco
	public Croco() {
		name = "croco";
		width = 100;
		height = 40;
		speed = 8;
		damage = 5;
		numSprites = 3;
		super.init();
	}
	
}
