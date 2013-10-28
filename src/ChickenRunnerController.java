import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class ChickenRunnerController {
	private final ChickenRunnerView view;
	private final ChickenRunnerModel model;
	
	public ChickenRunnerController(ChickenRunnerView view, ChickenRunnerModel model) {
		this.view = view;
		this.model = model;
		
		this.view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.view.setTitle("Andrew Zhao Chicken Runner");
		this.view.getContentPane().setPreferredSize(new Dimension(800, view.getBg1().getHeight(null)));
		this.view.pack();
		this.view.setLocationRelativeTo(null);
		
		this.view.addPanel(new Panel());
		this.view.setVisible(true);
		
		this.model.getBgPoint2().x = this.view.getBg2().getWidth(null);
		this.model.getBgPoint2().y = 0;
		
		this.model.setTimer(new Timer(5, new SideScroller()));
		this.model.getTimer().start();
	}
	
	class Panel extends JPanel {
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(view.getBg1(), model.getBgPoint1().x , model.getBgPoint1().y, view.getBg1().getWidth(null), view.getBg1().getHeight(null), null);
			g.drawImage(view.getBg2(), model.getBgPoint2().x , model.getBgPoint2().y, view.getBg2().getWidth(null), view.getBg1().getHeight(null), null);
		}
	}
	
	class SideScroller implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			moveBackground();
			view.repaint();
		}
		
	}
	
	public void moveBackground() {
		model.getBgPoint1().x -= 1;
		model.getBgPoint2().x -= 1;
		

		if (model.getBgPoint1().x <= view.getBg1().getWidth(null) * -1) {
			model.getBgPoint1().x = view.getBg1().getWidth(null);
			
		} else if (model.getBgPoint2().x <= view.getBg2().getWidth(null) * -1) {
			model.getBgPoint2().x = view.getBg2().getWidth(null);
		}
	}


	
}
