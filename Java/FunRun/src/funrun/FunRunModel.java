package funrun;

import java.util.ArrayList;

// THIS CLASS IS THE MODEL COMPONENT OF THE APPLICATION - HANDLES BACKEND/DATA/OBJECTS
public class FunRunModel {

	// create player object
	Player player;
	// create ArrayList to hold all of the Obstacle objects
	ArrayList<Obstacle> obstacles;
	// create ArrayList to hold all of the Nature objects (grass/flowers)
	ArrayList<Nature> nature;

	// constructor for the Model class - initializes player, ArrayLists, adds initial Obstacle and Nature objects
	public FunRunModel(FunRun gameController) {
		player = new Player();
		obstacles = new ArrayList<Obstacle>();
		nature = new ArrayList<Nature>();
		
		for(int i = 0; i < 15; i++) {
			addObstacle();
		}
		for(int i = 0; i < 10; i++) {
			nature.add(new Nature());
		}
	}
	
	// adds a random Obstacle object to the ArrayList. Probabilities are as follows:
	// 2/7 chance Stump
	// 2/7 chance Croco
	// 2/7 chance Spore
	// 1/7 chance Carrot - the player can eat the carrot for extra health
	public void addObstacle() {
		int type = (int) Math.floor(Math.random() * 35);
		if(type >= 0 && type < 10) {
			obstacles.add(new Stump());
		} else if(type >= 10 && type < 20) {
			obstacles.add(new Croco());
		} else if(type >= 20 && type < 30) {
			obstacles.add(new Spore());
		} else if(type >= 30) {
			obstacles.add(new Carrot());
		}
	}
	
}
