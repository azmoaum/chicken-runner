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
		
		this.view.getContentPane().setPreferredSize(new Dimension(this.model.getFrameWidth(), this.model.getFrameHeight()));
		this.view.pack();
		this.view.setLocationRelativeTo(null);
		
		this.view.addPanel(new Panel());
		
		this.model.setTimer(new Timer(this.model.getMainTimerDelay(), new MainTimer()));
		this.view.addKeyListener(new ChickenKeyListener());
		
		this.model.setAppleTimer(new Timer(this.model.getAppleSpawnDelay(), new AppleSpawner()));
		this.model.getAppleTimer().start();
		
		this.model.getTimer().start();
	}
	
	class Panel extends JPanel {
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.drawImage(view.getBg1(), model.getBgPoint1().x , model.getBgPoint1().y, view.getBg1().getWidth(null), view.getBg1().getHeight(null), null);
			g.drawImage(view.getBg2(), model.getBgPoint2().x , model.getBgPoint2().y, view.getBg2().getWidth(null), view.getBg1().getHeight(null), null);
			
			drawApple(g);
			
			g.drawImage(view.getCurrChickenImage(), model.getChicken().getPoint().x, model.getChicken().getPoint().y, view.getChickenImage().getWidth(null), view.getChickenImage().getHeight(null), null);
			
			for (Missle missle: model.getMissles()) {
				g.setColor(Color.RED);
				g.fillOval(missle.getPoint().x, missle.getPoint().y, missle.getMissleLength(), missle.getMissleLength());
			}
			
			drawScore(g);
		}

		public void drawScore(Graphics g) {
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			
			g.setFont(new Font("Monospaced", Font.BOLD, model.getFontSize()));
			g.setColor(new Color(0, 176, 80));
			g.drawString("Score " + model.getScore(), model.getScoreX(), model.getScoreY());
		}
		
		public void drawApple(Graphics g) {
			for (Apple apple: model.getApples()) {
				((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
	                    apple.getAlpha()));
				g.drawImage(view.getAppleImage(), apple.getPoint().x , apple.getPoint().y, view.getAppleImage().getWidth(null), view.getAppleImage().getHeight(null), null);
			}
			
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                   1.0f));
		}
	}
	
	class MainTimer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			moveBackground();
			moveChicken();
			moveApple();
			moveMissle();
			
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
	
	public void moveBackground() {
		model.getBgPoint1().x -= model.getBgDx();
		model.getBgPoint2().x -= model.getBgDx();

		if (model.getBgPoint1().x <= view.getBg1().getWidth(null) * -1) {
			model.getBgPoint1().x = view.getBg1().getWidth(null);
			
		} else if (model.getBgPoint2().x <= view.getBg2().getWidth(null) * -1) {
			model.getBgPoint2().x = view.getBg2().getWidth(null);
			
		}
	}
	
	public void moveChicken() {
		if (model.getChicken().isMoveRight()) {
			view.switchCurrChickenImage();
			
			//Is the user attempting to move the chicken outside the right boundary?
			if (model.getChicken().getPoint().x >= model.getFrameWidth() - view.getChickenImage().getWidth(null)) {
				model.getChicken().setMoveRight(false);
				return;
			}
			
			model.getChicken().moveRight();
		} else if (model.getChicken().isMoveLeft()) {
			view.switchCurrChickenImage();
			
			//Is the user attempting to move the chicken outside of the left boundary?
			if (model.getChicken().getPoint().x <= 0) {
				model.getChicken().setMoveLeft(false);
				return;
			}
			
			model.getChicken().moveLeft();
		}
		
		if (model.getChicken().isMoveUp()) {
			model.getChicken().moveUp();	
		}
	}
	
	public void moveApple() {
		int index = 0;
		for (Apple apple: model.getApples()) {
			apple.moveLeft();
			checkAppleChickenCollision();
			
			if (apple.isEaten()) {
				apple.getPoint().y -= 5;
				apple.setAlpha(apple.getAlpha() - 0.05f);
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
	
	public void checkAppleChickenCollision() {
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
	
	public void moveMissle() {
		for (Missle missle: model.getMissles()) {
			missle.moveRight();
		}
		
		if (!model.getMissles().isEmpty()) {
			if (model.getMissles().get(0).getPoint().x > model.getFrameWidth()) {
				model.getMissles().remove(0);
			}
		}
	}
	
	public void fireMissle() {
		int x = model.getChicken().getPoint().x + view.getChickenImage().getWidth(null);
		int y = model.getChicken().getPoint().y + model.getMissleYOffset();

		model.getMissles().add(new Missle(x, y));
	}
}
