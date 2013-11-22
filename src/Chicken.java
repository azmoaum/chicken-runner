import java.awt.Point;

public class Chicken extends Point {
	private boolean moveRight;
	private boolean moveLeft;
	private boolean moveUp;
	private int velY;
	private int velX;

	public Chicken() {
		this.setLocation(new Point(0, Constants.CHICKEN_START_Y));
		this.velX = Constants.CHICKEN_X_VELOCITY;
		this.velY = Constants.CHICKEN_Y_VELOCITY;
		this.moveRight = false;
		this.moveLeft = false;
		this.moveUp = false;
	}
	
	public boolean isMoveRight() {
		return moveRight;
	}

	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}

	public boolean isMoveLeft() {
		return moveLeft;
	}

	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}

	public boolean isMoveUp() {
		return moveUp;
	}

	public void setMoveUp(boolean moveUp) {
		this.moveUp = moveUp;
	}

	public void moveLeft() {
		this.setLocation(this.getX() - velX, this.getY());
	}
	
	public void moveRight() {
		this.setLocation(this.getX() + velX, this.getY());;
	}
	
	public void moveUp() {
		if (this.velY >= -Constants.CHICKEN_Y_VELOCITY) {
			this.setLocation(this.getX(), this.getY() - velY);
			this.velY -= Constants.CHICKEN_GRAVITY;
		} else {
			this.moveUp = false;
			this.velY = Constants.CHICKEN_Y_VELOCITY;
		}
	}
}
