import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ChickenRunnerController {
	private final ChickenRunnerView view;
	private final ChickenRunnerModel model;
	
	//Note: Is running two swing timers the best solution for two independent events
	// (moving the background, moving chicken, moving apple, spawning apple etc...)
	public ChickenRunnerController(ChickenRunnerView view, ChickenRunnerModel model) {
		this.view = view;
		this.model = model;
		
		//Set location of second background image to be on right side of window.
		this.model.getBgPoint2().x = this.view.getBg2().getWidth(null);
		
		this.view.addPanel(new Panel());
		
		this.model.setTimer(new Timer(Constants.MAIN_TIMER_DELAY, new MainTimer()));
		this.view.addKeyListener(new ChickenKeyListener());
		
		this.model.setAppleTimer(new Timer(Constants.APPLE_SPAWN_DELAY, new AppleSpawner()));
		this.model.getAppleTimer().start();
		
		this.model.setEnemyTimer(new Timer(Constants.ENEMY_SPAWN_DELAY, new EnemySpawner()));
		this.model.getEnemyTimer().start();
		
		this.model.getTimer().start();
	}
	
	class Panel extends JPanel {
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.drawImage(view.getBg1(), model.getBgPoint1().x , model.getBgPoint1().y, view.getBg1().getWidth(null), view.getBg1().getHeight(null), null);
			g.drawImage(view.getBg2(), model.getBgPoint2().x , model.getBgPoint2().y, view.getBg2().getWidth(null), view.getBg1().getHeight(null), null);
			
			drawHealthBar(g);
			
			drawApple(g);
			
			g.drawImage(view.getCurrChickenImage(), model.getChicken().getPoint().x, model.getChicken().getPoint().y, view.getChickenImage().getWidth(null), view.getChickenImage().getHeight(null), null);
			
			drawEnemy(g);
			
			for (Missle missle: model.getMissles()) {
				g.drawImage(view.getMissleImage(), missle.getPoint().x, missle.getPoint().y, missle.getMissleLength(), missle.getMissleLength(), null);
			}
			
			drawScore(g);
		}

		private void drawHealthBar(Graphics g) {
			//Draw Red health bar
			g.setColor(Constants.RED_COLOR);
			g.fillRect(Constants.HEALTHBAR_X, Constants.HEALTHBAR_Y, Constants.HEALTHBAR_WIDTH, Constants.HEALTHBAR_HEIGHT);
			
			//Draw Green health bar over top
			g.setColor(Constants.GREEN_COLOR);
			g.fillRect(model.getHealthBar().getX(), Constants.HEALTHBAR_Y, (int) (model.getHealthBar().getHealthPercentage() * Constants.HEALTHBAR_WIDTH), Constants.HEALTHBAR_HEIGHT);
			
			g.setFont(new Font("Monospaced", Font.BOLD, Constants.HEALTHBAR_FONT_SIZE));
			
			int healthPercentage = (int)(model.getHealthBar().getHealthPercentage() * 100);
			if (healthPercentage < 50) {
				g.setColor(Constants.RED_COLOR);
			}
			
			String str = healthPercentage + "%";
			int strLength = g.getFontMetrics().stringWidth(str);
			g.drawString(str, Constants.HEALTHBAR_PERCENT_X - strLength, Constants.HEALTHBAR_PERCENT_Y);
		}
		
		private void drawScore(Graphics g) {
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			
			g.setFont(new Font("Monospaced", Font.BOLD, Constants.SCORE_FONT_SIZE));
			g.setColor(Constants.GREEN_COLOR);
			g.drawString("Score " + model.getScore(), Constants.SCORE_X, Constants.SCORE_Y);
		}
		
		private void drawApple(Graphics g) {
			for (Apple apple: model.getApples()) {
				((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
	                    apple.getAlpha()));
				g.drawImage(view.getAppleImage(), apple.getPoint().x , apple.getPoint().y, view.getAppleImage().getWidth(null), view.getAppleImage().getHeight(null), null);
			}
			
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                   1.0f));
		}
		
		private void drawEnemy(Graphics g) {
			for (Enemy enemy: model.getEnemies()) {
				int shrinkFactor = 7 * (Constants.ENEMY_HEALTH_MAX - enemy.getHealth());
				g.drawImage(view.getCurrEnemyImage(), enemy.getPoint().x , enemy.getPoint().y + (shrinkFactor), view.getEnemyImage().getWidth(null) - shrinkFactor, view.getEnemyImage().getHeight(null) - shrinkFactor, null);
			}

		}
	}
	
	class MainTimer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			moveBackground();
			moveChicken();
			moveApple();
			moveMissle();
			moveEnemy();
			checkChickenEnemyCollision();
			view.repaint();
		}
		
	}
	
	class ChickenKeyListener implements KeyListener {
		//If you hold down a key. execute once [Pause] then execute repeatedly.
		//This boolean prevents repeating.
		boolean alreadyPressed = false;
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();

			switch (keyCode) { 
        		case KeyEvent.VK_UP:
        			model.getChicken().setMoveUp(true);
        			break;
        		case KeyEvent.VK_LEFT:
        			model.getChicken().setMoveLeft(true);
        			break;
        		case KeyEvent.VK_RIGHT:
        			model.getChicken().setMoveRight(true);
        			break;
        		case KeyEvent.VK_SPACE:
        			if (!alreadyPressed) {
        				fireMissle();
        			}
        			alreadyPressed = true;
        			break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			
			switch (keyCode) { 
				//Move Up is handled in Chicken Class.
        		case KeyEvent.VK_LEFT:
        			model.getChicken().setMoveLeft(false);
        			break;
        		case KeyEvent.VK_RIGHT :
        			model.getChicken().setMoveRight(false);
        			break;
        		case KeyEvent.VK_SPACE:
        			alreadyPressed = false;
        			break;
			}		
		}

		@Override
		public void keyTyped(KeyEvent e) {}
	}
	
	class AppleSpawner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.getApples().add(new Apple());
		}
	}
	
	class EnemySpawner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.getEnemies().add(new Enemy());
		}
		
	}
	
	private void moveBackground() {
		model.getBgPoint1().x -= Constants.BG_DX;
		model.getBgPoint2().x -= Constants.BG_DX;

		if (model.getBgPoint1().x <= view.getBg1().getWidth(null) * -1) {
			model.getBgPoint1().x = view.getBg1().getWidth(null);
			
		} else if (model.getBgPoint2().x <= view.getBg2().getWidth(null) * -1) {
			model.getBgPoint2().x = view.getBg2().getWidth(null);
			
		}
	}
	
	private void moveChicken() {
		if (model.getChicken().isMoveRight()) {
			//Is the user attempting to move the chicken outside the right boundary?
			if (model.getChicken().getPoint().x >= Constants.FRAME_WIDTH - view.getChickenImage().getWidth(null)) {
				model.getChicken().setMoveRight(false);
				return;
			}
			
			if (model.getChicken().getPoint().x % 3 == 0) {
				view.switchCurrChickenImage();
			}
			
			model.getChicken().moveRight();
		} else if (model.getChicken().isMoveLeft()) {
			//Is the user attempting to move the chicken outside of the left boundary?
			if (model.getChicken().getPoint().x <= 0) {
				model.getChicken().setMoveLeft(false);
				return;
			}
			
			if (model.getChicken().getPoint().x % 3 == 0) {
				view.switchCurrChickenImage();
			}
			
			model.getChicken().moveLeft();
		}
		
		if (model.getChicken().isMoveUp()) {
			model.getChicken().moveUp();	
		}
	}
	
	private void moveApple() {
		int index = 0;
		for (Apple apple: model.getApples()) {
			apple.moveLeft();
			checkAppleChickenCollision();
			
			if (apple.isEaten()) {
				apple.getPoint().y -= 5;
				apple.setAlpha(apple.getAlpha() - 0.04f);
				if (apple.getAlpha() < 0) {
					model.getApples().remove(index);
				}
			}
			index++;
		}
		
		if (!model.getApples().isEmpty()) {
			if (model.getApples().get(0).getPoint().x < -1 * view.getAppleImage().getWidth(null)) {
				model.getApples().remove(0);
			}
		}
	}
	
	private void checkAppleChickenCollision() {
		int index = 0;
		//Not the greatest way to check collisions. But it is a GOOD approximation.
		for (Apple apple: model.getApples()) {
			int appleChickenDistanceX = Math.abs((model.getChicken().getPoint().x - apple.getPoint().x));
			int appleChickenDistanceY = Math.abs((model.getChicken().getPoint().y - apple.getPoint().y));
			int appleWidth = view.getAppleImage().getWidth(null);
			int appleHeight = view.getAppleImage().getHeight(null);

			if (appleChickenDistanceY <= appleHeight && appleChickenDistanceX < appleWidth) {
				if (!apple.isEaten()) {
					model.addAppleScore();
				}
				model.getApples().get(index).setEaten(true);
			}
			
			index++;
		}
	}
	
	private void checkMissleEnemyCollision(Enemy enemy, int j) {
		int i = 0;
			for (Missle missle: model.getMissles()) {
				int enemyMissleDistanceX = Math.abs(missle.getPoint().x - enemy.getPoint().x);
				int enemyMissleDistanceY = Math.abs(missle.getPoint().y - enemy.getPoint().y);
				
				if (enemyMissleDistanceX <= 10 && enemyMissleDistanceY <= 50) {
					enemy.setHealth(enemy.getHealth() - 1);
					model.getMissles().remove(i);
					
					if (enemy.getHealth() <= 0) {
						model.getEnemies().remove(j);
					}
				}
				i++;
			}
	}
	
	private void checkChickenEnemyCollision() {
		//Not the greatest way to check collisions. But it is a GOOD approximation.
		for (Enemy enemy: model.getEnemies()) {
			int enemyChickenDistanceX = Math.abs((model.getChicken().getPoint().x - enemy.getPoint().x));
			int enemyChickenDistanceY = Math.abs((model.getChicken().getPoint().y - enemy.getPoint().y));
			int enemyWidth = view.getEnemyImage().getWidth(null);
			int enemyHeight = view.getEnemyImage().getHeight(null);

			if (enemyChickenDistanceY <= enemyHeight && enemyChickenDistanceX < enemyWidth) {
				model.getHealthBar().removeHealth();
			}
		}	
}
	
	private void moveMissle() {
		for (Missle missle: model.getMissles()) {
			missle.moveRight();
		}
		
		if (!model.getMissles().isEmpty()) {
			if (model.getMissles().get(0).getPoint().x > Constants.FRAME_WIDTH) {
				model.getMissles().remove(0);
			}
		}
	}
	
	private void fireMissle() {
		int x = model.getChicken().getPoint().x + view.getChickenImage().getWidth(null);
		int y = model.getChicken().getPoint().y + Constants.MISSLE_Y_OFFSET;

		model.getMissles().add(new Missle(x, y));
	}
	
	private void moveEnemy() {
		int index = 0;
		for (Enemy enemy: model.getEnemies()) {
			enemy.moveLeft();
			checkMissleEnemyCollision(enemy, index);
			
			index++;
		}
		
		if (!model.getEnemies().isEmpty()) {
			if (model.getEnemies().get(0).getPoint().x % 20 == 0) {
				view.switchCurrEnemyImage();
			}
			
			if (model.getEnemies().get(0).getPoint().x < -1 * view.getEnemyImage().getWidth(null)) {
				model.getEnemies().remove(0);
			}
		}
		
	}
}
