import java.awt.Point;

public class Missle {
	private final int X_VELOCITY = 5;
	private final int MISSLE_LENGTH = 7;
	
	private Point point;
	
	public Missle(int x, int y) {
		this.point = new Point(x, y);
	}
	
	public int getXVelocity() {
		return X_VELOCITY;
	}
	public int getMissleLength() {
		return MISSLE_LENGTH;
	}

	public Point getPoint() {
		return point;
	}
	
}
