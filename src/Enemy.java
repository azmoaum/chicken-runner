import java.awt.Point;

public class Enemy {
	private Point point;
	
	public Enemy() {
		this.point = new Point(Constants.ENEMY_START_X, Constants.ENEMY_START_Y);
	}

	public Point getPoint() {
		return point;
	}
	
	public void moveLeft() {
		this.point.x -= Constants.ENEMY_X_VELOCITY;
	}
	
}
