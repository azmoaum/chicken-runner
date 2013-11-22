public class HealthBar {
	private float health;
	
	public HealthBar() {
		this.health = Constants.HEALTH_MAX;
	}
	
	public void addHealth() {
		if (this.health < 100) {
			this.health += 1;
		}
	}
	
	public void removeHealth() {
		if (this.health > 0) {
			this.health -= 1;
		}
	}

	public float getHealth() {
		return this.health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getX() {
		return Constants.HEALTHBAR_X + Constants.HEALTHBAR_WIDTH - (int) (this.health / Constants.HEALTH_MAX * Constants.HEALTHBAR_WIDTH);
	}

	public float getHealthPercentage() {
		return this.health / Constants.HEALTH_MAX;
	}
	
}
