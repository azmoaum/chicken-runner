import java.awt.Point;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.Timer;

public class ChickenRunnerModel {
	private final int MAIN_TIMER_DELAY = 15;
	private final int FRAME_WIDTH = 800;
	private final int FRAME_HEIGHT = 400;
	private final int BG_DX = 2;
	private final int APPLE_SPAWN_DELAY = 1000;
	private final int APPLE_SCORE_VALUE = 250;
	private final int FONT_SIZE = 20;
	private final int SCORE_X = 10;
	private final int SCORE_Y = 20;
	private final int MISSLE_Y_OFFSET = 30;
	
	private int score;
	private Timer Timer, appleTimer;
	private Point bgPoint1, bgPoint2;
	private Chicken chicken;
	private CopyOnWriteArrayList<Missle> missles;
	private CopyOnWriteArrayList<Apple> apples;
	
	public ChickenRunnerModel() {
		this.score = 0;
		this.Timer = new Timer(0, null);
		this.appleTimer = new Timer(0, null);
		this.bgPoint1 = new Point(0, 0);
		this.bgPoint2 = new Point(0, 0);
		this.chicken = new Chicken();
		this.apples = new CopyOnWriteArrayList<Apple>();
		this.missles = new CopyOnWriteArrayList<Missle>();
		
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
	
	public CopyOnWriteArrayList<Apple> getApples() {
		return this.apples;
	}
	
	public CopyOnWriteArrayList<Missle> getMissles() {
		return this.missles;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void addAppleScore() {
		this.score += APPLE_SCORE_VALUE;
	}

	public int getScoreX() {
		return SCORE_X;
	}
	
	public int getScoreY() {
		return SCORE_Y;
	}
	
	public int getFontSize() {
		return FONT_SIZE;
	}
	
	public int getAppleSpawnDelay() {
		return APPLE_SPAWN_DELAY;
	}
	
	public int getMainTimerDelay() {
		return MAIN_TIMER_DELAY;
	}
	
	public int getMissleYOffset() {
		return MISSLE_Y_OFFSET;
	}
}
