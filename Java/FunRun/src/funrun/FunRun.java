package funrun;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

// THIS CLASS IS THE CONTROLLER COMPONENT OF THE APPLICATION - HANDLES USER INPUT/COMMUNICATION WITH VIEW AND MODEL
public class FunRun implements FunRunConstants, KeyListener {

	// create instances of the view and model classes
	FunRunView gameView;
	FunRunModel gameModel;

	// keep track of which keys are pressed on any given refresh
	boolean left = false, right = false, up = false, down = false;

	// all that the main needs to do is start it all off by initializing the
	// controller, which initializes everything else
	public static void main(String[] args) {
		new FunRun();
	}

	// constructor for the controller class - sets off all of the initialization
	// code
	public FunRun() {
		// initialize model and view classes
		gameModel = new FunRunModel(this);
		gameView = new FunRunView(this);

		// create a timer that refreshes the graphics every 40 milliseconds
		new Timer().scheduleAtFixedRate(new TimerTask() {
			// abstract method that must be implemented - what to do on each
			// refresh?
			public void run() {
				moveObjects(); // move everything
				checkCollisions(); // check for collisions
				gameView.repaint(); // repaint the graphics
				if (gameModel.player.health == 0) { // if the player is dead...
					cancel(); // then stop the timer
				}
			}
		}, 0, 40);
	}

	// required KeyListener method, but not used here
	public void keyTyped(KeyEvent e) {
	}

	// The next two methods, keyPressed() and keyReleased(), process the
	// player's movements. The player could be moved by just directly changing
	// the x and y coordinates for each key press/release, BUT this makes for
	// very jerky movement, since a held key sends signals at a fixed rate that
	// is much slower than the 40-millisecond refresh rate of the screen. For
	// this reason, I use booleans to determine if a key is pressed or not, so
	// that every time the screen is refreshed, it will move the player
	// according to which keys are pressed, regardless of the signal rate of the
	// KeyListener.

	// required KeyListener method, triggered whenever a key is pressed
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = true;
		}
	}

	// required KeyListener method, triggered whenever a key is released
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = false;
		}
	}

	// checks/updates the positions of all the objects on the screen
	private void moveObjects() {
		moveNature();
		movePlayer();
		moveObstacles();
	}

	// moves the background plants
	private void moveNature() {
		for (Nature n : gameModel.nature) {
			n.move();
		}
	}

	// moves the player
	private void movePlayer() {
		// used to keep track of the flashing when the player is hurt
		gameModel.player.hurt--;
		// update the player's position based on which key(s) are pressed down
		if (left) {
			gameModel.player.move(Player.LEFT);
		}
		if (right) {
			gameModel.player.move(Player.RIGHT);
		}
		if (up) {
			gameModel.player.move(Player.UP);
		}
		if (down) {
			gameModel.player.move(Player.DOWN);
		}
	}

	// moves all of the obstacles
	private void moveObstacles() {
		// check each obstacle
		for (int i = 0; i < gameModel.obstacles.size(); i++) {
			Obstacle o = gameModel.obstacles.get(i);
			// carrots have special movement rules (they try to run away from
			// you)
			if (o.name.equals("carrot")) {
				((Carrot) o).carrotMove(gameModel.player.x, gameModel.player.y, gameModel.player.height);
			}
			// move each obstacle
			o.move();

			// if the obstacle has passed off the screen...
			if (o.x < -o.width) {
				gameModel.player.score++; // increment the score
				// add another obstacle every 10 points
				if (gameModel.player.score != 0 && gameModel.player.score % 10 == 0) {
					gameModel.addObstacle();
				}

				// the following 2 lines make the types of obstacles change on
				// each pass, but with cost to the smoothness of the game
				/*
				gameModel.obstacles.remove(o);
				gameModel.addObstacle();
				*/

				// the following 3 lines make the game run more smoothly, but
				// the types of obstacles don't change
				o.x = APPLICATION_WIDTH + (int) Math.ceil(Math.random() * APPLICATION_WIDTH);
				o.y = (int) Math.floor(Math.random() * (APPLICATION_HEIGHT - o.height - ZERO)) + ZERO;
				o.collide = false;

				// ^these both work, but the first one makes it really freezy
			}
		}
	}

	// check to see if the player has collided with anything
	private void checkCollisions() {
		Player p = gameModel.player;
		// check each obstacle
		for (int i = 0; i < gameModel.obstacles.size(); i++) {
			Obstacle o = gameModel.obstacles.get(i);
			// if the obstacle is colliding with the player and it has not
			// already collided on this pass through
			if (!o.collide && p.x > o.x - p.width && p.x <= o.x + o.width && p.y >= o.y - p.height
					&& p.y <= o.y + o.height) {
				o.collide = true;
				p.health -= o.damage; // decrease player health
				// colliding into a carrot removes that carrot from the
				// ArrayList
				if (o.name.equals("carrot")) {
					gameModel.obstacles.remove(o);
					i++;
				} else { // if the player collides into anything else...
					p.x -= o.damage * 5; // the player gets knocked backwards
					if (p.x < 0) { // (as long as it's not knocked offscreen)
						p.x = 0;
					}
					p.hurt = 20; // this will trigger the flashing of the sprite
									// in the View
				}
				// health cannot go below zero
				if (p.health < 0) {
					p.health = 0;
				}
			}
		}
	}

	// The following 3 methods are used to grab the game objects from the View
	// component
	public Player getPlayer() {
		return gameModel.player;
	}

	public ArrayList<Obstacle> getObstacles() {
		return gameModel.obstacles;
	}

	public ArrayList<Nature> getNature() {
		return gameModel.nature;
	}

}
