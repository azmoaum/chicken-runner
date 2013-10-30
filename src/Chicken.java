import java.awt.Point;

public class Chicken {
	private final int GRAVITY = 1;
	private final int START_Y = 300;
	private final int X_VELOCITY = 4;
	private final int Y_VELOCITY = 20;
	
	private Point location;
	private boolean moveRight;
	private boolean moveLeft;
	private boolean moveUp;
	private int velY;
	private int velX;

	public Chicken() {
		this.location = new Point(0, START_Y);
		this.velX = X_VELOCITY;
		this.velY = Y_VELOCITY;
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

	public Point getPoint() {
		return this.location;
	}
	
	public void moveLeft() {
		this.location.x -= velX;
	}
	
	public void moveRight() {
		this.location.x += velX;
	}
	
	public void moveUp() {
		if (this.velY >= -Y_VELOCITY) {
			this.location.y -= velY;
			this.velY -= GRAVITY;
		} else {
			this.moveUp = false;
			this.velY = Y_VELOCITY;
		}
	}
}
