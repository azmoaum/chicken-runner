import java.awt.Point;

public class Missle extends Point {
	
	public Missle(int x, int y) {
		this.setLocation(new Point(x, y));
	}
	
	public int getXVelocity() {
		return Constants.MISSLE_X_VELOCITY;
	}
	
	public int getMissleLength() {
		return Constants.MISSLE_LENGTH;
	}

	
	public void moveRight() {
		this.setLocation(this.getX() + Constants.MISSLE_X_VELOCITY, this.getY());
	}
	
}
