import java.awt.Point;

import javax.swing.Timer;

public class Chicken {
	private Point location;
	private Timer timer;
	private boolean moveRight;
	private boolean moveLeft;
	private boolean moveUp;
	private boolean moveDown;
	private int dx;
	private int dy;
	
	public Chicken() {
		this.location = new Point(0, 300);
		this.dx = 2;
		this.dy = 2;
		this.moveRight = false;
		this.moveLeft = false;
		this.moveUp = false;
		this.moveDown = false;
	}
	
	public boolean isMoving() {
		return this.moveRight || this.moveLeft || this.moveUp || this.moveDown;
	}

	public void setMoving(boolean moving) {
		this.moveRight = false;
		this.moveLeft = false;
		this.moveUp = false;
		this.moveDown = false;
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

	public boolean isMoveDown() {
		return moveDown;
	}

	public void setMoveDown(boolean moveDown) {
		this.moveDown = moveDown;
	}
	
	public Point getPoint() {
		return this.location;
	}
	
	public void moveLeft() {
		this.location.x -= dx;
	}
	
	public void moveRight() {
		this.location.x += dx;
	}
	
	public void moveUp() {
		this.location.y -= dy;
	}
	
	public void moveDown() {
		this.location.y += dy;
	}
	
	public int getDx() {
		return this.dx;
	}
	
	public int getDy() {
		return this.dy;
	}
	
	public Timer getTimer() {
		return this.timer;
	}
	
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
}
