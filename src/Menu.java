import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Menu {
	private GamePanel gamePanel;
	private JButton restartButton;
	ActionListener restartButtonListener;
	
	
	public Menu(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		restartButton = new JButton("Restart");
		restartButton.setBounds(360, 270, 100, 40); 
		restartButton.setFocusable(false); 
		restartButton.setVisible(false);
		
		restartButtonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.restartGame();
			}
		};
		
		gamePanel.setLayout(null);         
		gamePanel.add(restartButton);
	}
	
	public void drawMainMenu(Graphics g) {
		
	}
	
	public void drawGameOver(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(new Font("Serif", Font.BOLD, 24));
		g.drawString("Game Over", 350, 250);
		restartButton.setVisible(true);
		
		restartButton.addActionListener(restartButtonListener);
	}

	public void hideRestart() {
		restartButton.setVisible(false);
	}
	
}
