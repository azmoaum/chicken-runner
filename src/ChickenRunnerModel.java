import java.awt.Point;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.Timer;

public class ChickenRunnerModel {
	private int score;
	private Timer Timer, appleTimer, enemyTimer;
	private Point bgPoint1, bgPoint2;
	private Chicken chicken;
	private CopyOnWriteArrayList<Missle> missles;
	private CopyOnWriteArrayList<Apple> apples;
	private CopyOnWriteArrayList<Enemy> enemies;
	private HealthBar healthBar;
	
	public ChickenRunnerModel() {
		this.score = 0;
		this.Timer = new Timer(0, null);
		this.appleTimer = new Timer(0, null);
		this.enemyTimer = new Timer(0, null);
		this.bgPoint1 = new Point(0, 0);
		this.bgPoint2 = new Point(0, 0);
		this.chicken = new Chicken();
		this.apples = new CopyOnWriteArrayList<Apple>();
		this.missles = new CopyOnWriteArrayList<Missle>();
		this.enemies = new CopyOnWriteArrayList<Enemy>();
		this.healthBar = new HealthBar();
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
	
	public Timer getEnemyTimer() {
		return enemyTimer;
	}

	public void setEnemyTimer(Timer enemyTimer) {
		this.enemyTimer = enemyTimer;
	}

	public Point getBgPoint1() {
		return this.bgPoint1;
	}
	
	public Point getBgPoint2() {
		return this.bgPoint2;
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
	
	public CopyOnWriteArrayList<Enemy> getEnemies() {
		return this.enemies;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void addAppleScore() {
		this.score += Constants.APPLE_SCORE_VALUE;
	}
	
	public HealthBar getHealthBar() {
		return this.healthBar;
	}
}
