import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
		
		this.view.getContentPane().setPreferredSize(new Dimension(model.getFrameWidth(), model.getFrameHeight()));
		this.view.pack();
		this.view.setLocationRelativeTo(null);
		
		this.view.addPanel(new Panel());
		
		this.model.setTimer(new Timer(15, new ChickenTimer()));
		this.view.addKeyListener(new ChickenKeyListener());
		
		this.model.setAppleTimer(new Timer(2000, new AppleSpawner()));
		this.model.getAppleTimer().start();
		
		this.model.setMissleTimer(new Timer(5, new MissleMover()));
		this.model.getMissleTimer().start();
		
		this.model.getTimer().start();
	}
	
	class Panel extends JPanel {
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.drawImage(view.getBg1(), model.getBgPoint1().x , model.getBgPoint1().y, view.getBg1().getWidth(null), view.getBg1().getHeight(null), null);
			g.drawImage(view.getBg2(), model.getBgPoint2().x , model.getBgPoint2().y, view.getBg2().getWidth(null), view.getBg1().getHeight(null), null);
			
			for (Apple apple: model.getApples()) {
				g.drawImage(view.getAppleImage(), apple.getPoint().x , apple.getPoint().y, view.getAppleImage().getWidth(null), view.getAppleImage().getHeight(null), null);
			}
			
			g.drawImage(view.getCurrChickenImage(), model.getChicken().getPoint().x, model.getChicken().getPoint().y, view.getChickenImage().getWidth(null), view.getChickenImage().getHeight(null), null);
			
			for (Missle missle: model.getMissles()) {
				g.setColor(Color.WHITE);
				g.fillOval(missle.getPoint().x, missle.getPoint().y, missle.getMissleLength(), missle.getMissleLength());
			}
		}
	}
	
	class ChickenTimer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			moveBackground();
			moveChicken();
			moveApple();
			
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
        			System.out.println(model.getApples().size());
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
	
	class MissleMover implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			moveMissle();
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
			model.getChicken().moveRight();
			view.switchCurrChickenImage();
		} else if (model.getChicken().isMoveLeft()) {
			model.getChicken().moveLeft();
			view.switchCurrChickenImage();
		}
		
		if (model.getChicken().isMoveUp()) {
			model.getChicken().moveUp();	
		}
	}
	
	public void moveApple() {
		for (Apple apple: model.getApples()) {
			apple.moveLeft();
			checkAppleChickenCollision();
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
				System.out.println("Apple eaten");
				model.getApples().remove(index);
			}
			index++;
		}
	}
	
	public void moveMissle() {
		for (Missle missle: model.getMissles()) {
			missle.getPoint().x += missle.getXVelocity();
		}
		
		if (!model.getMissles().isEmpty()) {
			if (model.getMissles().getFirst().getPoint().x > model.getFrameWidth()) {
				model.getMissles().removeFirst();
			}
		}
	}
	
	public void fireMissle() {
		int x = model.getChicken().getPoint().x + view.getChickenImage().getWidth(null);
		int y = model.getChicken().getPoint().y + 30;

		model.getMissles().addLast(new Missle(x, y));
	}
}
