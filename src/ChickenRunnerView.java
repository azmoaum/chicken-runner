import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ChickenRunnerView extends JFrame {
	private BufferedImage bg1, bg2; 
	private BufferedImage chickenImage, chickenImage2, chickenImage3;
	private BufferedImage currChickenImage;
	private BufferedImage appleImage;
	private BufferedImage missleImage;
	private BufferedImage enemyImage, enemyImage2;
	private BufferedImage currEnemyImage;
	
	public ChickenRunnerView() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Andrew Zhao Chicken Runner");
		this.setResizable(false);
		this.getContentPane().setPreferredSize(new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT));
		this.pack();
		this.setLocationRelativeTo(null);
		
		this.bg1 = null;
		this.bg2 = null;
		this.chickenImage = null;
		this.chickenImage2 = null;
		this.chickenImage3 = null;
		this.appleImage = null;
		this.missleImage = null;
		this.enemyImage = null;
		this.enemyImage2 = null;
		try {
		    this.bg1 = ImageIO.read(new File("images/Background.png"));
		    this.bg2 = ImageIO.read(new File("images/Background.png"));
		    this.chickenImage = ImageIO.read(new File("images/Chicken.png"));
		    this.chickenImage2 = ImageIO.read(new File("images/Chicken2.png"));
		    this.chickenImage3 = ImageIO.read(new File("images/Chicken3.png"));
		    this.appleImage = ImageIO.read(new File("images/Apple.png"));
		    this.missleImage = ImageIO.read(new File("images/Missle.png"));
		    this.enemyImage = ImageIO.read(new File("images/Enemy.png"));
		    this.enemyImage2 = ImageIO.read(new File("images/Enemy2.png"));
		} catch (IOException e) {

		}
		
		this.currChickenImage = this.chickenImage;
		this.currEnemyImage = this.enemyImage;
		
	}

	public Image getBg1() {
		return this.bg1;
	}
	
	public Image getBg2() {
		return this.bg2;
	}
	
	public Image getChickenImage() {
		return this.chickenImage;
	}
	
	public Image getChickenImage2() {
		return this.chickenImage;
	}
	
	public Image getCurrChickenImage() {
		return this.currChickenImage;
	}
	
	public void switchCurrChickenImage() {
		if (this.currChickenImage == this.chickenImage) {
			this.currChickenImage = this.chickenImage2;
		} else if (this.currChickenImage == this.chickenImage2){
			this.currChickenImage = this.chickenImage3;
		} else {
			this.currChickenImage = this.chickenImage;
		}
	}
	
	public Image getAppleImage() {
		return this.appleImage;
	}
	
	public Image getMissleImage() {
		return this.missleImage;
	}
	
	public Image getEnemyImage() {
		return this.enemyImage;
	}
	
	public Image getEnemy2Image() {
		return this.enemyImage2;
	}
	
	public Image getCurrEnemyImage() {
		return this.currEnemyImage;
	}
	
	public void switchCurrEnemyImage() {
		if (this.currEnemyImage == this.enemyImage) {
			this.currEnemyImage = enemyImage2;
		} else {
			this.currEnemyImage = this.enemyImage;
		}
	}
	
	public void addPanel(JPanel p) {
		this.add(p);
	}
	
	public static void main(String args[]) {
		ChickenRunnerModel model = new ChickenRunnerModel();
		ChickenRunnerView view = new ChickenRunnerView();
		ChickenRunnerController controller = new ChickenRunnerController(view, model);
		view.setVisible(true);
	}
}

