import java.awt.Point;
import javax.swing.Timer;

public class ChickenRunnerModel {
	private Timer timer;
	private Point bgPoint1, bgPoint2;
	private final int FRAME_WIDTH = 800;
	private final int FRAME_HEIGHT = 500;
	
	public ChickenRunnerModel() {
		this.timer = new Timer(0, null);
		this.bgPoint1 = new Point(0, 0);
		this.bgPoint2 = new Point(0, 0);
	}
	
	public Timer getTimer() {
		return this.timer;
	}
	
	public void setTimer(Timer timer) {
		this.timer = timer;
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
	
}
