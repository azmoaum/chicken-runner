import java.awt.Point;
import java.util.Random;

public class Apple extends Point {
	private boolean eaten;
	private float alpha;
	public Apple() {
		this.alpha = 1.0f;
		int startY = Constants.APPLE_START_Y;
		if (new Random().nextInt(3) + 1 == 3) {
			startY = Constants.APPLE_START_Y_2;
		}
		this.setLocation(new Point(Constants.APPLE_START_X, startY));
	}

	public void moveLeft() {
		this.setLocation(this.getX() - Constants.APPLE_X_VELOCITY, this.getY());
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
