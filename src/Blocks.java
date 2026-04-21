
import java.util.Random;
import java.awt.*;

public class Blocks {
	private Frame frame;
	private Random rand;
	private double x;
	private double y;
	private double gravity = 5;
	
	public Blocks(Frame frame) {
		this.frame = frame;
		this.rand = new Random();
		this.x = rand.nextDouble(10, frame.getFrameWidth()-60);
		this.y = 10;
	}
	
	public void draw(Graphics g) {
		if(getY() > 410) {
			return;
		}
		
		g.setColor(Color.BLUE);
		g.fillRect((int) x, (int) y, 50, 50);
	}
	
	public void remove(Graphics g) {
		g.setColor(new Color(0, 0, 0, 0));
	}
	
	public void blockMovement() {
		double fall = getY();
		fall += getGravity();
		setY(fall);
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}
}
