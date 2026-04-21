
import java.awt.*;
import javax.swing.*;

public class Player {
	private Frame frame;
	private double x;
	private double y;
	private double Vel;

	public Player(Frame frame) {
		this.frame = frame;
		this.x = frame.getFrameWidth()/2;
		this.y = frame.getFrameHeight()-100;
		this.Vel = 30;
	}
	
	public void draw(Graphics g) {
		// draw player
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y, 50, 50);
		
		// draw ground
		g.setColor(Color.GRAY);
		g.fillRect(0, 450, 800, 50);
	}
	
	public void reset() {
		setX(frame.getFrameWidth()/2);
	}
	
	public double getXValue() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getYValue() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVel() {
		return Vel;
	}

	public void setVel(double Vel) {
		this.Vel = Vel;
	}
}
