import java.awt.Point;

public class Apple {
	private final int X_VELOCITY = 2;
	private final int APPLE_START_Y = 125;
	private final int APPLE_START_X = 800;
	private final int SPAWN_TIME = 8000;
	
	private Point point;
	//private boolean eaten;
	
	public Apple() {
		this.point = new Point(APPLE_START_X, APPLE_START_Y);
		//this.eaten = false;
	}
	
	public int getSpawnTime() {
		return SPAWN_TIME;
	}

	public Point getPoint() {
		return this.point;
	}

	public void moveLeft() {
		this.point.x -= X_VELOCITY;
	}
	
	//public void spawn() {
		//this.eaten = false;
	//	this.point.x = APPLE_START_X;
	//}
	
	/*public boolean isEaten(){
		return this.eaten;
	}
	
	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}*/
	
	
}
