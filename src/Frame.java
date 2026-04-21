
import javax.swing.*;
import java.awt.event.*;

public class Frame {
	private JFrame frame;
	private GamePanel gamepanel;
	private JButton button;
	
	private int frameHeight = 500;
	private int frameWidth = 800;
	
	public Frame(){
		frame = new JFrame("DodgeTheBlocks");
		frame.setSize(frameWidth, frameHeight);
		gamepanel = new GamePanel(this);
		button = new JButton("Restart");
		
		
		frame.add(gamepanel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		gamepanel.requestFocus();
	}
	
	public double getFrameWidth() {
		return frameWidth;
	}
	
	public double getFrameHeight() {
		return frameHeight;
	}
}
