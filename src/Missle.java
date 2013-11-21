import java.awt.Point;

public class Missle {
	private Point point;
	
	public Missle(int x, int y) {
		this.point = new Point(x, y);
	}
	
	public int getXVelocity() {
		return Constants.MISSLE_X_VELOCITY;
	}
	
	public int getMissleLength() {
		return Constants.MISSLE_LENGTH;
	}

	public Point getPoint() {
		return point;
	}
	
	public void moveRight() {
		this.point.x += Constants.MISSLE_X_VELOCITY;
	}
	
}
