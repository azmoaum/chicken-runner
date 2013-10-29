import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.text.StyledEditorKit.ForegroundAction;

public class ChickenRunnerController {
	private final ChickenRunnerView view;
	private final ChickenRunnerModel model;
	
	//Note: Is running two swing timers the best solution for two independent events
	// (moving the background and controlling the chickens movement)
	public ChickenRunnerController(ChickenRunnerView view, ChickenRunnerModel model) {
		this.view = view;
		this.model = model;
		
		this.model.getBgPoint2().x = this.view.getBg2().getWidth(null);
		this.model.getBgPoint2().y = 0;
		
		this.view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.view.setTitle("Andrew Zhao Chicken Runner");
		this.view.getContentPane().setPreferredSize(new Dimension(800, view.getBg1().getHeight(null)));
		this.view.pack();
		this.view.setLocationRelativeTo(null);
		
		this.view.addPanel(new Panel());
		this.view.setVisible(true);
		
		this.model.setBgTimer(new Timer(20, new SideScroller()));
		this.view.addKeyListener(new ChickenKeyListener());
		this.model.getChicken().setTimer(new Timer(5, new ChickenActionListener()));
		
		this.model.getBgTimer().start();
	}
	
	class Panel extends JPanel {
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(view.getBg1(), model.getBgPoint1().x , model.getBgPoint1().y, view.getBg1().getWidth(null), view.getBg1().getHeight(null), null);
			g.drawImage(view.getBg2(), model.getBgPoint2().x , model.getBgPoint2().y, view.getBg2().getWidth(null), view.getBg1().getHeight(null), null);
			
			g.drawImage(view.getChickenImage(), model.getChicken().getPoint().x, model.getChicken().getPoint().y, view.getChickenImage().getWidth(null), view.getChickenImage().getHeight(null), null);
		}
	}
	
	class SideScroller implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			moveBackground();
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
        		case KeyEvent.VK_DOWN:
        			model.getChicken().setMoveDown(true);
        			break;
        		case KeyEvent.VK_LEFT:
        			model.getChicken().setMoveLeft(true);
        			break;
        		case KeyEvent.VK_RIGHT :
        			model.getChicken().setMoveRight(true);
        			break;
			}
			
			model.getChicken().getTimer().start();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
			int keyCode = e.getKeyCode();
			
			switch (keyCode) { 
        		case KeyEvent.VK_UP:
        			model.getChicken().setMoveUp(false);
        			break;
        		case KeyEvent.VK_DOWN:
        			model.getChicken().setMoveDown(false);
        			break;
        		case KeyEvent.VK_LEFT:
        			model.getChicken().setMoveLeft(false);
        			break;
        		case KeyEvent.VK_RIGHT :
        			model.getChicken().setMoveRight(false);
        			break;
			}
			//model.getChicken().setMoving(false);
			//model.getChicken().getTimer().stop();
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class ChickenActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (model.getChicken().isMoveRight()) {
				model.getChicken().moveRight();
			} else if (model.getChicken().isMoveLeft()) {
				model.getChicken().moveLeft();
			}
			
			if (model.getChicken().isMoveUp()) {
				model.getChicken().moveUp();
			} else if (model.getChicken().isMoveDown()) {
				model.getChicken().moveDown();
			}
		}
		
	}
	
	public void moveBackground() {
		model.getBgPoint1().x -= 2;
		model.getBgPoint2().x -= 2;

		if (model.getBgPoint1().x <= view.getBg1().getWidth(null) * -1) {
			model.getBgPoint1().x = view.getBg1().getWidth(null);
			
		} else if (model.getBgPoint2().x <= view.getBg2().getWidth(null) * -1) {
			model.getBgPoint2().x = view.getBg2().getWidth(null);
			
		}
	}

	public void moveChicken(int keyCode) {
		
		
	}

	
}
