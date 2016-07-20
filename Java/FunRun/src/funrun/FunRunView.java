package funrun;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;

// THIS CLASS IS THE VIEW COMPONENT OF THE APPLICATION - HANDLES GRAPHICS/JFRAME STUFF
public class FunRunView extends JFrame implements FunRunConstants {
	
	private static final long serialVersionUID = 1L;
	
	// create instance of the controller class
	FunRun gameController;
	
	// constructor for the view class - initializes JFrame, etc.
	public FunRunView(FunRun gameController) {
//		super("Display Screen");
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		
		addKeyListener(gameController);
		this.gameController = gameController;
		setVisible(true);
	}
	
	// Graphics method - draws everything
	public void paint(Graphics g) {
		drawBackground(g);
		Player p = gameController.getPlayer();
		drawPlayer(g, p);
		drawObstacles(g, p);
		drawStatus(g, p);
	}
	
	// draw the background (green background, black boundary line, flower/grass sprites)
	private void drawBackground(Graphics g) {
		g.setColor(new Color(0, 200, 0));
		g.fillRect(0, 0, APPLICATION_WIDTH, APPLICATION_HEIGHT);
		
		g.setColor(Color.black);
		g.drawLine(APPLICATION_WIDTH/2, 0, APPLICATION_WIDTH/2, APPLICATION_HEIGHT);
		
		for(Nature n : gameController.getNature()) {
			g.drawImage(n.sprite, n.x, n.y, n.width, n.height, null);
		}
	}
	
	// draw the player
	private void drawPlayer(Graphics g, Player p) {
		// determines which sprite to use based on the spriteCounter
		if((p.hurt < 0 || p.hurt % 2 == 0) && p.health > 0) {
			g.drawImage(p.sprites.get(p.spriteCount % 4), p.x, p.y, p.width, p.height, null);
		} else if(p.health == 0) {		// if game over, player is special angry bunny sprite
			g.drawImage(p.sprites.get(4), p.x, p.y, p.width, p.height, null);
		}
		p.spriteCount++;
	}
	
	// draw all obstacles
	private void drawObstacles(Graphics g, Player p) {
		g.setColor(Color.black);
		for(Obstacle o : gameController.getObstacles()) {
			g.drawImage(o.sprites.get(o.spriteCounter % o.numSprites), o.x, o.y, o.width, o.height, null);
		}
	}
	
	// draw the status strings (health, score, "GAME OVER" if you lose)
	private void drawStatus(Graphics g, Player p) {
		g.setColor(Color.white);
		g.drawString("HEALTH: " + p.health, 20, 60);
		g.drawString("SCORE: " + p.score, APPLICATION_WIDTH/2 - 90, 60);
		
		if(p.health == 0) {
			g.setFont(new Font("Helvetica", Font.BOLD, 100));
			g.setColor(Color.black);
			g.drawString("GAME OVER", 290, APPLICATION_HEIGHT/2 + 55);
			g.setColor(Color.red);
			g.drawString("GAME OVER", 285, APPLICATION_HEIGHT/2 + 50);
		}
	}
	
}
