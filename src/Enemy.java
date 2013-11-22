import java.awt.Point;

public class Enemy {
	private Point point;
	private int health;
	
	public Enemy() {
		this.point = new Point(Constants.ENEMY_START_X, Constants.ENEMY_START_Y);
		this.health = Constants.ENEMY_HEALTH_MAX;
	}

	public Point getPoint() {
		return point;
	}
	
	public void moveLeft() {
		this.point.x -= Constants.ENEMY_X_VELOCITY;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
}
