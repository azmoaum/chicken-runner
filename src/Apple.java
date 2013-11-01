import java.awt.Point;

public class Apple {
	private final int X_VELOCITY = 2;
	private final int APPLE_START_Y = 125;
	private final int APPLE_START_X = 800;

	private Point point;
	private boolean eaten;
	private float alpha;
	
	public Apple() {
		this.alpha = 1.0f;
		this.point = new Point(APPLE_START_X, APPLE_START_Y);
	}

	public Point getPoint() {
		return this.point;
	}

	public void moveLeft() {
		this.point.x -= X_VELOCITY;
	}

	public boolean isEaten() {
		return eaten;
	}

	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	
	
	
}
