import java.awt.Point;
import javax.swing.Timer;

public class ChickenRunnerModel {
	private Timer Timer, appleTimer;
	private Point bgPoint1, bgPoint2;
	private Chicken chicken;
	private Apple apple;
	private final int FRAME_WIDTH = 800;
	private final int FRAME_HEIGHT = 400;
	private final int BG_DX = 2;
	
	public ChickenRunnerModel() {
		this.Timer = new Timer(0, null);
		this.appleTimer = new Timer(0, null);
		this.bgPoint1 = new Point(0, 0);
		this.bgPoint2 = new Point(0, 0);
		this.chicken = new Chicken();
		this.apple = new Apple();
	}
	
	public Timer getTimer() {
		return this.Timer;
	}
	
	public void setTimer(Timer timer) {
		this.Timer = timer;
	}
	
	public void setAppleTimer(Timer timer) {
		this.appleTimer = timer;
	}
	
	public Timer getAppleTimer() {
		return this.appleTimer;
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
	
	public int getBgDx() {
		return this.BG_DX;
	}
	
	public Chicken getChicken() {
		return this.chicken;
	}
	
	public Apple getApple() {
		return this.apple;
	}
	
}
