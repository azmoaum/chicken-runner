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
		
		this.model.setAppleTimer(new Timer(8000, new AppleSpawner()));
		this.model.getAppleTimer().start();
		
		this.model.getTimer().start();
	}
	
	class Panel extends JPanel {
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.drawImage(view.getBg1(), model.getBgPoint1().x , model.getBgPoint1().y, view.getBg1().getWidth(null), view.getBg1().getHeight(null), null);
			g.drawImage(view.getBg2(), model.getBgPoint2().x , model.getBgPoint2().y, view.getBg2().getWidth(null), view.getBg1().getHeight(null), null);
			
			if (!model.getApple().isEaten()) {
				g.drawImage(view.getAppleImage(), model.getApple().getPoint().x , model.getApple().getPoint().y, view.getAppleImage().getWidth(null), view.getAppleImage().getHeight(null), null);
			}
			
			g.drawImage(view.getCurrChickenImage(), model.getChicken().getPoint().x, model.getChicken().getPoint().y, view.getChickenImage().getWidth(null), view.getChickenImage().getHeight(null), null);
		
		}
	}
	
	class ChickenTimer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			moveBackground();
			moveChicken();

			if (!model.getApple().isEaten()) {
				model.getApple().moveLeft();
				checkAppleChickenCollision();
			}
			
			view.repaint();
		}
		
	}
	
	class ChickenKeyListener implements KeyListener {

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
        		case KeyEvent.VK_RIGHT :
        			model.getChicken().setMoveRight(true);
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
			}		
		}

		@Override
		public void keyTyped(KeyEvent e) {}
	}
	
	class AppleSpawner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.getApple().spawn();
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
	
	public void checkAppleChickenCollision() {
		int appleChickenDistanceX = Math.abs((model.getChicken().getPoint().x - model.getApple().getPoint().x));
		
		int appleWidth = view.getAppleImage().getWidth(null);
		
		if (model.getChicken().getPoint().y <= model.getApple().getPoint().y
				&& appleChickenDistanceX < appleWidth) {
			System.out.println("Apple eaten");
			model.getApple().setEaten(true);
		}
	}
}
