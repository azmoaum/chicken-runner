import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChickenRunnerView extends JFrame {
	private BufferedImage bg1;
	
	public ChickenRunnerView() {
		this.bg1 = null;
		try {
		    this.bg1 = ImageIO.read(new File("images/Background.png"));
		} catch (IOException e) {
		}
		
	}

	public Image getBg1() {
		return this.bg1;
	}

	public void addPanel(JPanel p) {
		this.add(p);
	}
	
	public static void main(String args[]) {
		ChickenRunnerModel model = new ChickenRunnerModel();
		ChickenRunnerView view = new ChickenRunnerView();
		ChickenRunnerController controller = new ChickenRunnerController(view, model);
	}
}

