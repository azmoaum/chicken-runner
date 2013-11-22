import java.awt.Point;

public class Enemy extends Point{
	private int health;
	private boolean jumpedOn;
	
	public Enemy() {
		this.setLocation(new Point(Constants.ENEMY_START_X, Constants.ENEMY_START_Y));
		this.health = Constants.ENEMY_HEALTH_MAX;
	}

	public void moveLeft() {
		this.setLocation(this.getX() - Constants.ENEMY_X_VELOCITY, this.getY());
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isJumpedOn() {
		return jumpedOn;
	}

	public void setJumpedOn(boolean jumpedOn) {
		this.jumpedOn = jumpedOn;
	}
	
}
