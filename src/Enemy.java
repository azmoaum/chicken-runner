import java.awt.Point;

public class Enemy {
	private Point point;
	
	private final int X_VELOCITY = 2;
	private final int ENEMY_START_X = 800;
	private final int ENEMY_START_Y = 310;
	
	public Enemy() {
		this.point = new Point(ENEMY_START_X, ENEMY_START_Y);
	}

	public Point getPoint() {
		return point;
	}
	
	public void moveLeft() {
		this.point.x -= X_VELOCITY;
	}
	
}
