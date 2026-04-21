
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
	private Frame frame;
	private GamePanel gamepanel;
	private Menu menu;
	private Player player;
	private Blocks blocks;
	private Timer timer;
	private JButton restartButton;
	
	
	private boolean gameOver;
	private int scoreCount;
	private int highScore;
	
	int finishFrame;
	private List<Blocks> blockList;
	
	private long lastInputTime;
	private final int INPUTDELAY = 200;
	private final int FPS = 60;
	private final int DELAY = 1000 / FPS;
	private int frameCount = 0;
	private int spawnRate = 30;
	
	public GamePanel(Frame frame) {
		this.frame = frame;
		this.gameOver = false;
		this.blockList = new ArrayList<>();
		this.player = new Player(frame);
		this.menu = new Menu(this);
		this.blocks = new Blocks(frame);
		
		addKeyListener(this);
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.RED);
		g.drawString("Score: "+scoreCount, 10, 15);
		
		g.setColor(Color.GREEN);
		g.drawString("High Score: "+highScore, 10, 35);
		
		player.draw(g);
		
		for(Blocks b : blockList) {
			b.draw(g);
		}
		
		if(gameOver) {
			menu.drawGameOver(g);
			return;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (gameOver) {
			if (scoreCount > highScore) {
			    highScore = scoreCount;
			}
	        return;
	    }
	
		if(scoreCount % 50 == 0 && spawnRate > 20) {
			spawnRate--;
			for(Blocks b : blockList) {
				b.setGravity(b.getGravity()+0.1);
			}
		}
			
		frameCount++;
		
		if(frameCount % spawnRate == 0) blockList.add(spawnBlock(this.frame));
		
		if(frameCount % 5 == 0) scoreCount++;
		
		Iterator<Blocks> iterator = blockList.iterator();
		
		while(iterator.hasNext()) {
			Blocks b = iterator.next();
			b.blockMovement();
			
			if (b.getY() > 410) {
	            iterator.remove();
	        }
			
			onCollision(b);
			
			if (gameOver) {
				break; 
			}
		}
		
		repaint();
	}
	
	private Blocks spawnBlock(Frame frame) {
		return blocks = new Blocks(frame);
	}
	
	public void restartGame() {
		blockList.clear();
		player.reset();           
		gameOver = false;
		frameCount = 0;
		scoreCount = 0;
		spawnRate = 30;
		
		menu.hideRestart();
		
		timer.start();

		requestFocus();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(gameOver) return;
		
		long currentTime = System.currentTimeMillis();
		
		
		if(currentTime - lastInputTime < INPUTDELAY) {
			return;
		}
		
		lastInputTime = currentTime;
		
		int keyCode = e.getKeyCode();
		double move = player.getXValue();
		
		if(keyCode == KeyEvent.VK_LEFT) {
			move = Math.max(0, move - player.getVel());
		}
		else if (keyCode == KeyEvent.VK_RIGHT && move + 50 < frame.getFrameWidth()) {
			move += player.getVel(); 
		}
		player.setX(move);
	}
	
	private void onCollision(Blocks b) {
		if(b.getX() < player.getXValue() && b.getX()+50 > player.getXValue() 
				|| player.getXValue() < b.getX() && player.getXValue()+50 > b.getX()) {
			
			if(b.getY()+50 > player.getYValue() && b.getY() < player.getYValue()) {
				gameOver = true;
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
