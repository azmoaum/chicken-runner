import java.awt.Point;
import javax.swing.Timer;

public class ChickenRunnerModel {
	private Timer bgTimer;
	private Point bgPoint1, bgPoint2;
	private Chicken chicken;
	private final int FRAME_WIDTH = 800;
	private final int FRAME_HEIGHT = 500;
	
	public ChickenRunnerModel() {
		this.bgTimer = new Timer(0, null);
		this.bgPoint1 = new Point(0, 0);
		this.bgPoint2 = new Point(0, 0);
		this.chicken = new Chicken();
	}
	
	public Timer getBgTimer() {
		return this.bgTimer;
	}
	
	public void setBgTimer(Timer timer) {
		this.bgTimer = timer;
	}
	
	public Point getBgPoint1() {
		return this.bgPoint1;
	}
	
	public Point getBgPoint2() {
		return this.bgPoint2;
	}
	
	public int getFrameWidth() {
		return this.FRAME_WIDTH;
	}
	
	public int getFrameHeight() {
		return this.FRAME_HEIGHT;
	}
	
	public Chicken getChicken() {
		return this.chicken;
	}
	
}
